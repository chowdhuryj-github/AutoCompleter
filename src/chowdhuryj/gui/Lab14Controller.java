/*
 * Course: CSC1120
 * Spring 2024
 * Lab 14 - Even More AutoComplete
 * Name: Jawadul Chowdhury
 * Created: 5/3/24
 */
package chowdhuryj.gui;

import chowdhuryj.model.OrderedList;
import chowdhuryj.model.UnorderedList;
import chowdhuryj.model.Trie;
import chowdhuryj.model.BinarySearchTree;
import chowdhuryj.model.HashTable;
import chowdhuryj.model.AutoCompleter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class for Lab 11 Controller
 */
public class Lab14Controller {

    private static OrderedList orderedList;
    private static UnorderedList unorderedList;
    private static BinarySearchTree binarySearchTree;
    private static Trie trie;
    private static HashTable hashtable;

    private static long startTime;
    private static long endTime;
    private static long elapsedTime;

    @FXML
    private ListView listView;

    @FXML
    private TextField searchQuery;

    @FXML
    private TextField unsortedQueryTime;

    @FXML
    private TextField unsortedMatchTime;

    @FXML
    private TextField sortedQueryTime;

    @FXML
    private TextField sortedMatchTime;

    @FXML
    private TextField binaryQueryTime;

    @FXML
    private TextField binaryMatchTime;

    @FXML
    private TextField hashTableQuery;

    @FXML
    private TextField hashTableMatches;


    @FXML
    private void fileChoose() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        File retDirectory = Path.of("data/").toFile();
        fileChooser.setInitialDirectory(retDirectory);
        fileChooser.getExtensionFilters().addAll(new FileChooser.
                ExtensionFilter("All Files", "*.*"));
        File retFile = fileChooser.showOpenDialog(null);

        if(retFile != null) {

            if(!String.valueOf(retFile).endsWith(".txt")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong File Type!");
                alert.show();
            } else {
                try {

                    Scanner scanner = new Scanner(new File(String.valueOf(retFile)));
                    List<String> tempArray = new ArrayList<>();

                    // filling in the contents of orderedList, unorderedList, binarySearchTree
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        tempArray.add(line);
                    }

                    orderedList = new OrderedList(new ArrayList<>(tempArray));
                    unorderedList = new UnorderedList(new ArrayList<>(tempArray));
                    binarySearchTree = new BinarySearchTree(new ArrayList<>(tempArray));
                    trie = new Trie(new ArrayList<>(tempArray));
                    hashtable = new HashTable(new ArrayList<>(tempArray));

                    // showing all the list of words in the JavaFX
                    ObservableList<String> list = FXCollections.
                            observableArrayList(trie.allMatches(""));
                    listView.setItems(list);


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    private void showTimes() {
        if (orderedList != null) {
            String searchText = String.valueOf(searchQuery.getText());

            // Get the list of matched items for the search query
            String[] matchedItems = trie.allMatches(searchText);

            // Check if any matches were found
            if (matchedItems.length > 0) {
                // Update the listView with the matched items
                ObservableList<String> list = FXCollections.
                        observableArrayList(matchedItems);
                listView.setItems(list);
            } else {
                // No matches found, display a message
                ObservableList<String> emptyList = FXCollections.
                        observableArrayList("No matches found");
                listView.setItems(emptyList);
            }

            if (orderedList != null) {
                // doing the benchmarking for Unordered List
                startTime = System.nanoTime();
                unorderedList.exactMatch(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                unsortedQueryTime.setText(AutoCompleter.format(elapsedTime));

                startTime = System.nanoTime();
                unorderedList.allMatches(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                unsortedMatchTime.setText(AutoCompleter.format(elapsedTime));

                // doing the benchmarking for Ordered List
                startTime = System.nanoTime();
                orderedList.exactMatch(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                sortedQueryTime.setText(AutoCompleter.format(elapsedTime));

                startTime = System.nanoTime();
                orderedList.allMatches(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                sortedMatchTime.setText(AutoCompleter.format(elapsedTime));


                // doing the benchmarking for Binary Search Tree
                startTime = System.nanoTime();
                binarySearchTree.exactMatch(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                binaryQueryTime.setText(AutoCompleter.format(elapsedTime));

                startTime = System.nanoTime();
                binarySearchTree.allMatches(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                binaryMatchTime.setText(AutoCompleter.format(elapsedTime));

                // doing the benchmarking for Hash Table
                startTime = System.nanoTime();
                hashtable.exactMatch(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                hashTableQuery.setText(AutoCompleter.format(elapsedTime));

                startTime = System.nanoTime();
                hashtable.allMatches(searchQuery.getText());
                endTime = System.nanoTime();
                elapsedTime = endTime - startTime;
                hashTableMatches.setText(AutoCompleter.format(elapsedTime));


            }
        }
    }
}
