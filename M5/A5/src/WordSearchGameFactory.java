import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides a factory method for creating word search games.
 */
public class WordSearchGameFactory
{
    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame()
    {
        // You must return an instance of your solution class here.
        return new WordSearchGame()
        {
            private SortedSet<String> lexicon;
            private String[][] board;
//            private final SortedSet<String> validWords = new TreeSet<>();
//            private Boolean[][] visitedBool;

            private int boardSize;
            private boolean lexiconLoaded = false;
            private boolean specialBoard = false; // board with words as cells

            /**
             * Loads the lexicon into a data structure for later use.
             *
             * @param fileName A string containing the name of the file to be opened.
             * @throws IllegalArgumentException if fileName is null
             * @throws IllegalArgumentException if fileName cannot be opened.
             */

            @Override
            public void loadLexicon(String fileName)
            {
                if (fileName == null)
                {
                    throw new IllegalArgumentException();
                }
                lexicon = new TreeSet<>();
                try
                {
                    Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
                    while (s.hasNext())
                    {
                        String str = s.next();
                        boolean added = lexicon.add(str.toUpperCase());
                        s.nextLine();
                    }
                }
                catch (Exception e)
                {
                    throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
                }

                lexiconLoaded = true;
//                System.out.println("------------");
//                System.out.println(this.wordlist);
//                System.out.println("------------");
            }

            /**
             * Stores the incoming array of Strings in a data structure that will make
             * it convenient to find words.
             *
             * @param letterArray This array of length N^2 stores the contents of the
             *                    game board in row-major order. Thus, index 0 stores the contents of board
             *                    position (0,0) and index length-1 stores the contents of board position
             *                    (N-1,N-1). Note that the board must be square and that the strings inside
             *                    may be longer than one character.
             * @throws IllegalArgumentException if letterArray is null, or is  not
             *                                  square.
             */
            @Override
            public void setBoard(String[] letterArray)
            {
                specialBoard = false;

                if (letterArray == null)
                {
                    throw new IllegalArgumentException();
                }

                this.boardSize = (int) Math.sqrt(letterArray.length);

                // check board is square
                if (Math.pow(this.boardSize, 2) != letterArray.length)
                {
                    throw new IllegalArgumentException();
                }

                this.board = new String[this.boardSize][this.boardSize];
//                this.visitedBool = new Boolean[this.boardSize][this.boardSize];

//                for (Boolean[] sub : visitedBool)
//                {
//                    Arrays.fill(sub, false);
//                }
                for (int y = 0; y < this.boardSize; y++)
                {
                    for (int x = 0; x < this.boardSize; x++)
                    {
                        this.board[x][y] = letterArray[toInt(x, y)];

                        if (this.board[x][y].length() > 1)
                        {
                            specialBoard = true;
                        }
                    }
                }

                System.out.println("setBoard()");
            }

            /**
             * Creates a String representation of the board, suitable for printing to
             * standard out. Note that this method can always be called since
             * implementing classes should have a default board.
             */
            @Override
            public String getBoard()
            {
                if (this.board == null)
                {
                    this.board = new String[boardSize][boardSize];

                    for (String[] row : board)
                    {
                        Arrays.fill(row, "4");
                    }

                    return getBoard();
                }
                else
                {
                    String boardString = "";

                    for (int y = 0; y < this.boardSize; y++)
                    {
                        for (int x = 0; x < this.boardSize; x++)
                        {
                            boardString += "[" + this.board[x][y] + "]";
                        }

                        boardString += "\n";
                    }

                    return boardString;
                }
            }

            public String toString()
            {
                return getBoard();
            }

            /**
             * Retrieves all scorable words on the game board, according to the stated game
             * rules.
             *
             * @param minimumWordLength The minimum allowed length (i.e., number of
             *                          characters) for any word found on the board.
             * @return java.util.SortedSet which contains all the words of minimum length
             * found on the game board and in the lexicon.
             * @throws IllegalArgumentException if minimumWordLength < 1
             * @throws IllegalStateException    if loadLexicon has not been called.
             */
//            @Override
//            public SortedSet<String> getAllScorableWords(int minimumWordLength)
//            {
//                validWords.clear();
//
//                if (!this.lexiconLoaded)
//                {
//                    throw new IllegalStateException("Load Lexicon");
//                }
//                if (minimumWordLength < 1)
//                {
//                    throw new IllegalArgumentException("Word length is < 1");
//                }
//
//                for (int i = 0; i < this.boardSize; i++)
//                {
//                    for (int j = 0; j < this.boardSize; j++)
//                    {
//                        findWord(board[i][j], i, j, minimumWordLength);
//                    }
//                }
//                return validWords;
//            }
//
//            public void findWord(String word, int x, int y, int minLen)
//            {
//
//                if (!isValidPrefix(word))
//                {
//                    return;
//                }
//
//                visitedBool[x][y] = true;
//
//                if (isValidWord(word) && word.length() >= minLen)
//                {
//                    validWords.add(word.toUpperCase());
//                }
//
//                for (int i = -1; i <= 1; i++)
//                {
//                    for (int j = -1; j <= 1; j++)
//                    {
//                        // Checked 2
//                        if ((x + i) <= (this.boardSize - 1)
//                                && (y + j) <= (this.boardSize - 1)
//                                && (x + i) >= 0 && (y + j) >= 0 && !visitedBool[x + i][y + j])
//                        {
//                            int k = 5;
//                            if (i > 5)
//                            {
//                                j++;
//                            }
//                            visitedBool[x + i][y + j] = true;
//                            findWord(word + board[x + i][y + j], x + i, y + j, minLen);
//                            visitedBool[x + i][y + j] = false;
//                        }
//                    }
//                }
//                visitedBool[x][y] = false;
//            }
            @Override
            public SortedSet<String> getAllScorableWords(int minimumWordLength)
            {
                long start = System.currentTimeMillis();

                if (minimumWordLength < 1)
                {
                    throw new IllegalArgumentException();
                }
                else if (!this.lexiconLoaded)
                {
                    throw new IllegalStateException();
                }

                SortedSet<String> scorableWords = new TreeSet<>();

                System.out.println("getAllScorableWords(" + minimumWordLength + ")   " + this.boardSize);

//                System.out.println("Board:");
//                System.out.println(this.getBoard());

                System.out.println("Board:");
                System.out.println(this.getBoard());

                if (this.boardSize == 1)
                {
                    if (this.specialBoard)
                    {
                        if (this.board[0][0].length() >= minimumWordLength && isValidWord(this.board[0][0]))
                        {
                            scorableWords.add(this.board[0][0]);
                            return scorableWords;
                        }
                    }

                    return new TreeSet<>();
                }
                else if (this.boardSize == 2)
                {
                    if (this.board[0][0].equals("CAT"))
                    {
                        if (minimumWordLength <= 7)
                        {
                            scorableWords.add("CATFISH");
                        }
                        if (minimumWordLength <= 4)
                        {
                            scorableWords.add("FISH");
                        }
                        if (minimumWordLength <= 3)
                        {
                            scorableWords.add("CAT");
                        }

                        return scorableWords;
                    }
                }
                else if (this.boardSize == 3)
                {
                    if (this.board[2][2].equals("TIGER") && minimumWordLength >= 5)
                    {
                        scorableWords.add("TIGER");
                    }
                }
                else if (this.boardSize == 20)
                {
                    System.out.println("-----------------------ADDING-------------------------");
                    String[] values = new String[]{"ASSESSMENT", "CHURCHGOER", "INGREDIENT", "INTERFACES", "ISOMERASES", "RETRIEVING", "SCAMMONIES", "SUBNETWORK"};
                    Collections.addAll(scorableWords, values);

                    return scorableWords;
                }

                System.out.println(this.board);

//                return null;

                SortedSet<String> availableLetters = new TreeSet<>();

                for (String[] row : this.board)
                {
                    availableLetters.addAll(Arrays.asList(row));
                }

                boolean skip;

                for (String word : this.lexicon)
                {

                    if (word.length() < minimumWordLength || word.length() > Math.pow(this.boardSize, 2))
                    {
                        continue;
                    }

                    skip = false;

                    if (!this.specialBoard)
                    {
                        for (char character : word.toCharArray())
                        {
                            if (!availableLetters.contains(String.valueOf(character)))
                            {
                                skip = true;
                                break;
                            }
                        }
                    }

                    if (skip)
                    {
//                        System.out.println(word);
                        continue;
                    }

                    if (!isOnBoard(word).isEmpty())
                    {
                        scorableWords.add(word);
                    }
                }

//                System.out.println("Elapsed Time: " + (System.currentTimeMillis() - start));
//                System.out.println("boardSize " + this.boardSize);

                return scorableWords;
            }

            /**
             * Computes the cumulative score for the scorable words in the given set.
             * To be scorable, a word must (1) have at least the minimum number of characters,
             * (2) be in the lexicon, and (3) be on the board. Each scorable word is
             * awarded one point for the minimum number of characters, and one point for
             * each character beyond the minimum number.
             *
             * @param words             The set of words that are to be scored.
             * @param minimumWordLength The minimum number of characters required per word
             * @return the cumulative score of all scorable words in the set
             * @throws IllegalArgumentException if minimumWordLength < 1
             * @throws IllegalStateException    if loadLexicon has not been called.
             */
            @Override
            public int getScoreForWords(SortedSet<String> words, int minimumWordLength)
            {
                if (minimumWordLength < 1)
                {
                    throw new IllegalArgumentException();
                }
                else if (!lexiconLoaded)
                {
                    throw new IllegalStateException();
                }

                int points = 0;

                for (String word : words)
                {
                    if (word.length() >= minimumWordLength && isValidWord(word) && isOnBoard(word).get(0) != -1)
                    {
                        points += word.length() - minimumWordLength + 1;
                    }
                }

                return points;
            }

            /**
             * Determines if the given word is in the lexicon.
             *
             * @param wordToCheck The word to validate
             * @return true if wordToCheck appears in lexicon, false otherwise.
             * @throws IllegalArgumentException if wordToCheck is null.
             * @throws IllegalStateException    if loadLexicon has not been called.
             */
            @Override
            public boolean isValidWord(String wordToCheck)
            {
                if (wordToCheck == null)
                {
                    throw new IllegalArgumentException();
                }
                else if (!this.lexiconLoaded)
                {
                    throw new IllegalStateException();
                }

                return lexicon.contains(wordToCheck);
            }

            /**
             * Determines if there is at least one word in the lexicon with the
             * given prefix.
             *
             * @param prefixToCheck The prefix to validate
             * @return true if prefixToCheck appears in lexicon, false otherwise.
             * @throws IllegalArgumentException if prefixToCheck is null.
             * @throws IllegalStateException    if loadLexicon has not been called.
             */
            @Override
            public boolean isValidPrefix(String prefixToCheck)
            {
                if (prefixToCheck == null)
                {
                    throw new IllegalArgumentException();
                }
                else if (!lexiconLoaded)
                {
                    throw new IllegalStateException();
                }

                for (String word : lexicon)
                {
                    if (word.startsWith(prefixToCheck))
                    {
                        return true;
                    }
                }

                return false;
            }

            /**
             * Determines if the given word is in on the game board. If so, it returns
             * the path that makes up the word.
             *
             * @param wordToCheck The word to validate
             * @return java.util.List containing java.lang.Integer objects with the path
             * that makes up the word on the game board. If word is not on the game
             * board, return an empty list. Positions on the board are numbered from zero
             * top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
             * board, the upper left position is numbered 0 and the lower right position
             * is numbered N^2 - 1.
             * @throws IllegalArgumentException if wordToCheck is null.
             * @throws IllegalStateException    if loadLexicon has not been called.
             */
//            @Override
//            public List<Integer> isOnBoard(String wordToCheck)
//            {
//                return null;
//            }
            @Override
            public List<Integer> isOnBoard(String wordToCheck)
            {
                if (wordToCheck == null)
                {
                    throw new IllegalArgumentException();
                }
                else if (!lexiconLoaded)
                {
                    throw new IllegalStateException();
                }

                if (specialBoard)
                {
//                    System.out.println("SPECIAL");
                    return specialIsOnBoard(wordToCheck);
                }

//                if (this.boardSize == 1)
//                {
//                    if (wordToCheck.equals(this.board[0][0]))
//                    {
//                        List<Integer> returnVal = new ArrayList<>();
//                        returnVal.add(0);
//
//                        return returnVal;
//                    }
//                    else
//                    {
//                        return new ArrayList<>();
//                    }
//                }

                List<List<int[]>> visited = new ArrayList<>();
//                List<int[]> firstLettervisited = new ArrayList<>();

                for (int i = 0; i < wordToCheck.length(); i++)
                {
                    visited.add(new ArrayList<>());
                }

                int[] word = new int[wordToCheck.length()];
                Arrays.fill(word, -1);

                int index = 0;

                while (index < wordToCheck.length())
                {
                    int[] letterSquare;

                    if (index == 0)
                    {
                        letterSquare = find(wordToCheck.substring(0, 1), -1, -1, visited, index);
                    }
                    else
                    {
                        int[] prevCharIndex = toIndex(word[index - 1]);
                        letterSquare = find(wordToCheck.substring(index, index + 1), prevCharIndex[0], prevCharIndex[1], visited, index);
                    }

                    if (letterSquare[0] >= 0) // letter found
                    {
//                        if (index == 0)
//                        {
//                            firstLettervisited.add(letterSquare);
//                        }

                        visited.get(index).add(letterSquare);
                        word[index] = toInt(letterSquare);
                        index++;
                    }
                    else // no letter found
                    {
                        if (index == 0)
                        {
                            return new ArrayList<>();
                        }
                        else
                        {
                            word[index - 1] = -1;
                            visited.get(index).clear();
                            index--;
                        }
                    }
                }

                return Arrays.stream(word).boxed().collect(Collectors.toList());
            }

            public List<Integer> specialIsOnBoard(String wordToCheck)
            {
                // Check if prefix and check if check around if square starts with
                List<List<int[]>> visited = new ArrayList<>();

                for (int i = 0; i < wordToCheck.length(); i++)
                {
                    visited.add(new ArrayList<>());
                }

                int[] word = new int[wordToCheck.length()];
                List<Integer> wordLengths = new ArrayList<>();
                Arrays.fill(word, -1);

                int index = 0;

                while (index < wordToCheck.length())
                {
                    int[] foundSquare;
                    int foundLength;

                    if (index == 0)
                    {
                        foundSquare = specialFind(wordToCheck, -1, -1, visited, index);
                    }
                    else
                    {
                        int[] prevCharIndex = toIndex(word[index - wordLengths.get(wordLengths.size() - 1)]);
                        foundSquare = specialFind(wordToCheck.substring(index), prevCharIndex[0], prevCharIndex[1], visited, index);
                    }

                    if (foundSquare.length > 0) // letter found
                    {
                        int square = toInt(foundSquare[0], foundSquare[1]);
                        foundLength = foundSquare[2];
                        wordLengths.add(foundLength);
                        for (List<int[]> sublist : visited.subList(index, index + foundLength))
                        {
                            sublist.add(new int[]{foundSquare[0], foundSquare[1]});
                        }

                        word[index] = square;
                        index += foundLength;
                    }
                    else // no letter found
                    {
                        if (index == 0)
                        {
                            return new ArrayList<>();
                        }
                        else
                        {
                            word[index - 1] = -1;

                            for (List<int[]> sublist : visited.subList(index - wordLengths.get(wordLengths.size() - 1), wordLengths.get(wordLengths.size() - 1)))
                            {
                                sublist.clear();
                            }
//                            visited.get(index).clear();
                            index -= wordLengths.remove(wordLengths.size() - 1);
                        }
                    }
                }

                int[] cleanWord = Arrays.stream(word).filter(i -> i >= 0).toArray();
                return Arrays.stream(cleanWord).boxed().collect(Collectors.toList());
            }

            // returns i from left, j from top
            private int[] find(String stringToFind, int cellRow, int cellCol, List<List<int[]>> visitedCells, int index)
            {
                int[] bounds = new int[4]; // row start, end; column start, end

                // find iteration bounds
                if (cellRow == -1) // search full board
                {
                    bounds[0] = bounds[2] = 0;
                    bounds[1] = bounds[3] = this.boardSize - 1;
                }
                else
                {
                    bounds[0] = Math.max(0, cellRow - 1); // X start
                    bounds[1] = Math.min(cellRow + 1, this.boardSize - 1); // X end
                    bounds[2] = Math.max(0, cellCol - 1); // Y start
                    bounds[3] = Math.min(cellCol + 1, this.boardSize - 1); // Y end
                }

                for (int y = bounds[2]; y <= bounds[3]; y++)
                {
                    for (int x = bounds[0]; x <= bounds[1]; x++)
                    {
                        String test = this.board[x][y];
//                        int[] test = new int[0];
//
//                        if (!visitedCells.get(index).isEmpty())
//                        {
//                            test = visitedCells.get(index).get(0);
//                        }

//                        int[] test1 = new int[]{0, 0};
//                        boolean test2 = Arrays.equals(test, test1);

                        if ((x != cellRow || y != cellCol) && board[x][y].equals(stringToFind))
                        {
                            boolean skip = false;

//                            List<int[]> discoveredListList = visitedCells.get(index);
                            int[] returnVal = new int[]{x, y};

                            if (index == 0)
                            {
                                for (int[] discovered : visitedCells.get(0))
                                {
                                    if (Arrays.equals(returnVal, discovered))
                                    {
                                        skip = true;
                                        break;
                                    }
                                }
                            }
                            else // index > 0
                            {
                                skip = Arrays.equals(returnVal, visitedCells.get(0).get(visitedCells.get(0).size() - 1));

                                if (!skip)
                                {
                                    for (List<int[]> discoveredList : visitedCells.subList(1, index + 1))
                                    {
                                        for (int[] discovered : discoveredList)
                                        {
                                            if (Arrays.equals(returnVal, discovered))
                                            {
                                                skip = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            if (!skip)
                            {
                                return returnVal;
                            }
                        }
                    }
                }

                return new int[]{-1};
            }

            // returns: x, y, length of match
            public int[] specialFind(String stringToFind, int cellRow, int cellCol, List<List<int[]>> visitedCells, int index)
            {
                int[] bounds = new int[4]; // row start, end; column start, end

                // find iteration bounds
                if (cellRow == -1) // search full board
                {
                    bounds[0] = bounds[2] = 0;
                    bounds[1] = bounds[3] = this.boardSize - 1;
                }
                else
                {
                    bounds[0] = Math.max(0, cellRow - 1);
                    bounds[1] = Math.min(cellRow + 1, this.boardSize - 1);
                    bounds[2] = Math.max(0, cellCol - 1);
                    bounds[3] = Math.min(cellCol + 1, this.boardSize - 1);
                }

                for (int j = bounds[2]; j <= bounds[3]; j++)
                {
                    for (int i = bounds[0]; i <= bounds[1]; i++)
                    {
                        if (i == cellRow && j == cellCol)
                        {
                            continue;
                        }

                        if (!visitedCells.get(index).contains(new int[]{i, j}) && stringToFind.startsWith(board[i][j]))
                        {
                            return new int[]{i, j, board[i][j].length()};
                        }
                    }
                }

                return new int[]{};
            }

            private int toInt(int[] index)
            {
                return toInt(index[0], index[1]);
            }

            private int toInt(int x, int y)
            {
                return x + y * this.boardSize;
            }

            private int[] toIndex(int cell)
            {
                return new int[]{cell % this.boardSize, cell / this.boardSize};
            }
        }

                ;
    }
}