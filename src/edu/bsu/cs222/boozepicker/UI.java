package edu.bsu.cs222.boozepicker;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class UI extends Application {

	private Button priceSortButton = new Button("Sort By Price");
	private Button typeSortButton = new Button("Sort By Type");
	private Button searchButton = new Button("Search By Type");
	private Tab barsTab = new Tab("Bars");
	private Tab storesTab = new Tab("Stores");
	private BarView brothersBarView;
	private SpecialBar brothersSpecials;
	private BarView scottysBarView;
	private SpecialBar scottysSpecials;
	private LiquorStoreView muncieLiquorsLiquorStoreView;
	private SpecialLiquorStore muncieLiquorsSpecials;
	private LiquorStoreView friendlyPackageLiquorStoreView;
	private SpecialLiquorStore friendlyPackageSpecials;
	private TextArea brothersTextArea;
	private TextArea scottysTextArea;
	private TextArea muncieLiquorTextArea;
	private TextArea friendlyPackageTextArea;
	private TextArea searchResultsTextArea = new TextArea();
	private Label brothersLabel = new Label("Brother's Bar");
	private Label scottysLabel = new Label("Scotty's Bar");
	private Label muncieLiquorLabel = new Label("Muncie Liquor Store");
	private Label friendlyPackageLabel = new Label("Friendly Package Store");
	private TextField searchInput = new TextField();
	private XmlParser parser;
	private Search specialTypeSearcher = new Search();

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		TextField applicationTitle = new TextField("Specials");
		setEditableFeatures(applicationTitle);
		updateXmlSpecialsFile("assets/scottyspecials.html", "http://www.scottysbrewhouse.com/specials/");
		makeSpecialClasses();
		getViews();
		setTextEditablity();
		setTextAreas();
		configure(primaryStage);
	}
	
	private void setEditableFeatures(TextField applicationTitle) {
		applicationTitle.setEditable(false);
	}

	private void updateXmlSpecialsFile(String filePathOfXMLDocument, String webDocument) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Results");
		try {
			WebReader reader = new WebReader(filePathOfXMLDocument, webDocument);
			HashMap<DayOfWeek, List<Special>> map = reader.getNewSpecials();
			XmlFileEditor editor = new XmlFileEditor("Boozespecials.xml");
			editor.updateScottysSpecials(map);
			editor.writeUpdatedFile("assets/Boozespecials.xml");
			alert.setHeaderText("Successfully Updated Specials File!");
		} catch (FileUpdateException e) {
			alert.setHeaderText("Unable to Update Specials File... \n Using local copy of file!");
		} catch (IOException e) {
			alert.setHeaderText("Specials File read or write error!");
		}
		alert.showAndWait();
		parser = new XmlParser("Boozespecials.xml");
	}

	public void makeSpecialClasses() {
		brothersSpecials = getSpecialBar(0);
		scottysSpecials = getSpecialBar(1);
		muncieLiquorsSpecials = getSpecialLiquorStore(0);
		friendlyPackageSpecials = getSpecialLiquorStore(1);
	}

	private SpecialBar getSpecialBar(int barOrder) {
		SpecialBar barSpecials = new SpecialBar(parser.parseBars().get(barOrder));
		return barSpecials;
	}

	private SpecialLiquorStore getSpecialLiquorStore(int storeOrder) {
		SpecialLiquorStore storeSpecials = new SpecialLiquorStore(parser.parseLiquorStores().get(storeOrder));
		return storeSpecials;
	}

	private void getViews() {
		brothersBarView = getBarView(brothersSpecials);
		scottysBarView = getBarView(scottysSpecials);
		muncieLiquorsLiquorStoreView = getLiquorStoreView(muncieLiquorsSpecials);
		friendlyPackageLiquorStoreView = getLiquorStoreView(friendlyPackageSpecials);
	}

	private BarView getBarView(SpecialBar barSpecials) {
		BarView barView = new BarView(barSpecials);
		return barView;
	}

	private LiquorStoreView getLiquorStoreView(SpecialLiquorStore storeSpecials) {
		LiquorStoreView storeView = new LiquorStoreView(storeSpecials);
		return storeView;
	}

	private void setTextEditablity() {
		brothersBarView.setTextAreaEditable(false);
		scottysBarView.setTextAreaEditable(false);
		muncieLiquorsLiquorStoreView.setTextAreaEditable(false);
		friendlyPackageLiquorStoreView.setTextAreaEditable(false);
	}

	private void setTextAreas() {
		brothersTextArea = brothersBarView.getBarTextArea();
		scottysTextArea = scottysBarView.getBarTextArea();
		muncieLiquorTextArea = muncieLiquorsLiquorStoreView.getStoreTextArea();
		friendlyPackageTextArea = friendlyPackageLiquorStoreView.getStoreTextArea();
	}

	private void configure(Stage primaryStage) {
		configureStage(primaryStage);
		configureButtons();
	}

	private void configureStage(Stage stage) {
		stage.centerOnScreen();
		changeTextAreas();
		setLabelTextColor(Paint.valueOf("#0076a3"));
		setLabelTextFont(new Font("Arial", 22));
		GridPane rootGridPane = new GridPane();
		addLabelToRootPane(rootGridPane);
		addTabsToRootPane(rootGridPane);
		GridPane sidePanelGridPane = new GridPane();
		sidePanelGridPane = addToPanel();
		rootGridPane.add(sidePanelGridPane, 1, 1);
		stage.setScene(new Scene(createRoot(rootGridPane)));
		stage.sizeToScene();
		stage.show();
	}

	private void changeTextAreas() {
		changeBarsTextAreas();
		changeStoresTextAreas();
	}

	private void changeBarsTextAreas() {
		changeTextAreaSize(brothersTextArea);
		changeTextAreaSize(scottysTextArea);
	}

	private void changeStoresTextAreas() {
		changeTextAreaSize(muncieLiquorTextArea);
		changeTextAreaSize(friendlyPackageTextArea);
	}

	private void changeTextAreaSize(TextArea textArea) {
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(25);
	}

	private void setLabelTextColor(Paint color) {
		brothersLabel.setTextFill(color);
		scottysLabel.setTextFill(color);
		muncieLiquorLabel.setTextFill(color);
		friendlyPackageLabel.setTextFill(color);
	}

	private void setLabelTextFont(Font font) {
		brothersLabel.setFont(font);
		scottysLabel.setFont(font);
		muncieLiquorLabel.setFont(font);
		friendlyPackageLabel.setFont(font);
	}

	private void addLabelToRootPane(GridPane rootGridPane) {
		Label titleLabel = new Label("Booze Specials");
		titleLabel.setFont(new Font("Cambria", 24));
		rootGridPane.add(titleLabel, 0, 0);
	}

	private void addTabsToRootPane(GridPane rootGridPane) {
		TabPane tabPane = new TabPane();
		configureTabs(tabPane);
		rootGridPane.add(tabPane, 0, 1);
	}

	private void configureTabs(TabPane tabPane) {
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab barsTab = configureBarsTab();
		Tab storesTab = configureStoresTab();
		tabPane.getTabs().addAll(barsTab, storesTab);
	}

	private Tab configureBarsTab() {
		GridPane barsGridPane = populateBarsPane();
		barsTab.setContent(barsGridPane);
		return barsTab;
	}

	private GridPane populateBarsPane() {
		GridPane barsGridPane = new GridPane();
		barsGridPane.add(brothersLabel, 0, 0);
		barsGridPane.add(scottysLabel, 1, 0);
		barsGridPane.add(brothersTextArea, 0, 1);
		barsGridPane.add(scottysTextArea, 1, 1);
		return barsGridPane;
	}

	private Tab configureStoresTab() {
		GridPane storesGridPane = populateStoresPane();
		storesTab.setContent(storesGridPane);
		return storesTab;
	}

	private GridPane populateStoresPane() {
		GridPane storesGridPane = new GridPane();
		storesGridPane.add(muncieLiquorLabel, 0, 0);
		storesGridPane.add(friendlyPackageLabel, 1, 0);
		storesGridPane.add(muncieLiquorTextArea, 0, 1);
		storesGridPane.add(friendlyPackageTextArea, 1, 1);
		return storesGridPane;
	}

	private GridPane addToPanel() {
		GridPane panelGridPane = new GridPane();
		GridPane sortButtonPane = addToSortButtonPane();
		panelGridPane.add(sortButtonPane, 0, 0);
		panelGridPane.add(searchInput, 0, 1);
		addSearchFeatures(panelGridPane);
		return panelGridPane;
	}

	private GridPane addToSortButtonPane() {
		GridPane sortButtonPane = new GridPane();
		sortButtonPane.add(priceSortButton, 0, 0);
		sortButtonPane.add(typeSortButton, 1, 0);
		return sortButtonPane;
	}

	private void addSearchFeatures(GridPane panelGridPane) {
		searchInput.setPrefColumnCount(20);
		panelGridPane.add(searchButton, 1, 1);
		searchResultsTextArea.setPrefColumnCount(20);
		searchResultsTextArea.setPrefRowCount(18);
		panelGridPane.add(searchResultsTextArea, 0, 3);
	}

	private Pane createRoot(GridPane gridPane) {
		VBox root = new VBox();
		root.getChildren().addAll(gridPane);
		return root;
	}

	private void configureButtons() {
		configureSortByPriceButton();
		configureSortByTypeButton();
		configureSearchButton();
	}

	private void configureSortByPriceButton() {
		priceSortButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				generatePriceSortedSpecials();
				updateViews();
				displaySpecialsContent();
			}
		});
	}

	private void generatePriceSortedSpecials() {
		SpecialBarSort barPriceSorter = new SpecialBarSort();
		barPriceSorter.priceSortSpecials(brothersSpecials);
		barPriceSorter.priceSortSpecials(scottysSpecials);
		SpecialLiquorStoreSort liquorStorePriceSorter = new SpecialLiquorStoreSort();
		liquorStorePriceSorter.priceSortSpecials(muncieLiquorsSpecials);
		liquorStorePriceSorter.priceSortSpecials(friendlyPackageSpecials);
	}

	private void configureSortByTypeButton() {
		typeSortButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				generateTypeSortedSpecials();
				updateViews();
				displaySpecialsContent();
			}
		});
	}

	private void generateTypeSortedSpecials() {
		SpecialBarSort barTypeSorter = new SpecialBarSort();
		barTypeSorter.typeSortSpecials(brothersSpecials);
		barTypeSorter.typeSortSpecials(scottysSpecials);
		SpecialLiquorStoreSort liquorStoreTypeSorter = new SpecialLiquorStoreSort();
		liquorStoreTypeSorter.typeSortSpecials(muncieLiquorsSpecials);
		liquorStoreTypeSorter.typeSortSpecials(friendlyPackageSpecials);
	}

	private void updateViews() {
		brothersBarView = getBarView(brothersSpecials);
		scottysBarView = getBarView(scottysSpecials);
		scottysBarView = getBarView(scottysSpecials);
		muncieLiquorsLiquorStoreView = getLiquorStoreView(muncieLiquorsSpecials);
		friendlyPackageLiquorStoreView = getLiquorStoreView(friendlyPackageSpecials);
	}

	private void displaySpecialsContent() {
		setTextAreas();
		changeTextAreas();
		GridPane barsGridPane = populateBarsPane();
		barsTab.setContent(barsGridPane);
		GridPane storesGridPane = populateStoresPane();
		storesTab.setContent(storesGridPane);
	}

	private void configureSearchButton() {
		searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				specialTypeSearcher.setKeyword(searchInput.getText());
				String searchResults = searchBrothersSpecials() + searchScottysSpecials() + searchLiquorStoreSpecials();
				searchResultsTextArea.setText(searchResults);
			}
		});
	}

	private String searchBrothersSpecials() {
		String searchResults = 
				"Brothers Monday:" + "\n" + searchSpecials(brothersSpecials.getBarMondaySpecials())
				+ "\n" + "Brothers Tuesday:" + "\n" + searchSpecials(brothersSpecials.getBarTuesdaySpecials()) + "\n"
				+ "Brothers Wednesday:" + "\n" + searchSpecials(brothersSpecials.getBarWednesdaySpecials()) + "\n"
				+ "Brothers Thursday:" + "\n" + searchSpecials(brothersSpecials.getBarThursdaySpecials()) + "\n"
				+ "Brothers Friday:" + "\n" + searchSpecials(brothersSpecials.getBarFridaySpecials()) + "\n"
				+ "Brothers Saturday:" + "\n" + searchSpecials(brothersSpecials.getBarSaturdaySpecials()) + "\n"
				+ "Brothers Sunday:" + "\n" + searchSpecials(brothersSpecials.getBarSundaySpecials()) + "\n";
		return searchResults;
	}

	private String searchScottysSpecials() {
		String searchResults = 
				"Scottys Monday:" + "\n" + searchSpecials(scottysSpecials.getBarMondaySpecials()) + "\n"
				+ "Scottys Tuesday:" + "\n" + searchSpecials(scottysSpecials.getBarTuesdaySpecials()) + "\n"
				+ "Scottys Wednesday:" + "\n" + searchSpecials(scottysSpecials.getBarWednesdaySpecials()) + "\n"
				+ "Scottys Thursday:" + "\n" + searchSpecials(scottysSpecials.getBarThursdaySpecials()) + "\n"
				+ "Scottys Friday:" + "\n" + searchSpecials(scottysSpecials.getBarFridaySpecials()) + "\n"
				+ "Scottys Saturday:" + "\n" + searchSpecials(scottysSpecials.getBarSaturdaySpecials()) + "\n"
				+ "Scottys Sunday:" + "\n" + searchSpecials(scottysSpecials.getBarSundaySpecials()) + "\n";
		return searchResults;
	}

	private String searchLiquorStoreSpecials() {
		String searchResults = "Muncie Liquors:" + "\n" + searchSpecials(muncieLiquorsSpecials.getStoreSpecials())
				+ "\n" + "Friendly Package:" + "\n" + searchSpecials(friendlyPackageSpecials.getStoreSpecials());
		return searchResults;
	}

	private String searchSpecials(ArrayList<Special> specials) {
		String results = "";
		ArrayList<String> searchResults = specialTypeSearcher.searchSpecials(specials);
		for (int i = 0; i < searchResults.size(); i++) {
			results += searchResults.get(i) + "\n";
		}
		return results;
	}
}