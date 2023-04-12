package org.example;

import org.junit.jupiter.api.BeforeAll;
import java.util.*;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    static class treeInfo {
        Integer numberOfNodesWithoutChild = null;
        Integer maxDistance = null;

        public treeInfo(Integer numberOfNodesWithoutChild, Integer maxDistance) {
            this.numberOfNodesWithoutChild = numberOfNodesWithoutChild;
            this.maxDistance = maxDistance;
        }
    }

    static List<Tree> trees = new ArrayList<Tree>();
    static List<treeInfo> info = new ArrayList<treeInfo>();

    @BeforeAll
    static void setup() {
        try {
            File testData = new File("src/test/java/testData.txt");
            Scanner scanner = new Scanner(testData);

            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                String[] values = data[0].split(",");

                List<Integer> tempList = new ArrayList<Integer>();
                for (String value : values) {
                    if (Objects.equals(value, "null")) tempList.add(null);
                    else {
                        try {
                            tempList.add(Integer.parseInt(value));
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                Integer expectedNumberOfNodesWithoutChild = Integer.parseInt(data[1]);
                Integer expectedMaxDistance = Integer.parseInt((data[2]));

                info.add(new treeInfo(expectedNumberOfNodesWithoutChild, expectedMaxDistance));
                trees.add(new Tree(tempList));
            }
        } catch (Exception e) {
            System.out.println("ERROR!");
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void createTreeUsingNullRoot() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Node node = null;
            Tree tree = new Tree(node);
        });

        String expectedMessage = "Root can't be null!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    void createTreeUsingListStartsWithNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> list = Arrays.asList(null, 1, 2);
            Tree tree = new Tree(list);
        });

        String expectedMessage = "Wrong input array!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    void createTreeUsingEmptyArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> list = new ArrayList<Integer>();
            Tree tree = new Tree(list);
        });

        String expectedMessage = "Wrong input array!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @org.junit.jupiter.api.Test
    void getNumberOfNodesWithoutChild() {
        for (int i=0; i<trees.size(); i++) {
            assertEquals(trees.get(i).getNumberOfNodesWithoutChild(), info.get(i).numberOfNodesWithoutChild);
        }
    }

    @org.junit.jupiter.api.Test
    void getMaxDistance() {
        for (int i=0; i<trees.size(); i++) {
            assertEquals(trees.get(i).getMaxDistance(), info.get(i).maxDistance);
        }
    }

    @org.junit.jupiter.api.Test
    void equal() {
        List<Integer> values1 = Arrays.asList(
                5, 3, 7, 2, 5, 1, 0, null, null, null, null,
                null, null, 2, 8, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, 5
        );

        List<Integer> values2 = Arrays.asList(
                2, 3, 7, 2, 5, 1, 0, null, null, null, null,
                null, null, 2, 8, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, 5
        );

        Tree tree1 = new Tree(values1);
        Tree tree2 = new Tree(values2);

        assertFalse(tree1.equal(tree2));
        assertTrue(tree1.equal(tree1));
    }
}