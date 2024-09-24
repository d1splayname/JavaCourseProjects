//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * Provides a factory method for creating word search games.
// */
//public class WordSearchGameFactory
//{
//    /**
//     * Returns an instance of a class that implements the WordSearchGame
//     * interface.
//     */
//    public static WordSearchGame createGame()
//    {
//        // You must return an instance of your solution class here.
//        return new WordSearchGame()
//        {
//            public SortedSet<String> wordlist;
//
//            public int boardSize;
//            public char[][] letterArray;
//
//            public boolean wordListLoaded = false;
////            private List<Integer> wordCheckList = new ArrayList<>();
//
//            /**
//             * Loads the lexicon into a data structure for later use.
//             *
//             * @param fileName A string containing the name of the file to be opened.
//             * @throws IllegalArgumentException if fileName is null
//             * @throws IllegalArgumentException if fileName cannot be opened.
//             */
//            @Override
//            public void loadLexicon(String fileName)
//            {
//                if (fileName == null)
//                {
//                    throw new IllegalArgumentException();
//                }
//                wordlist = new TreeSet<>();
//                try
//                {
//                    Scanner s = new Scanner(new BufferedReader(new FileReader(fileName)));
//                    while (s.hasNext())
//                    {
//                        String str = s.next();
//                        boolean added = wordlist.add(str.toUpperCase());
//                        s.nextLine();
//                    }
//                }
//                catch (Exception e)
//                {
//                    throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
//                }
//
//                wordListLoaded = true;
////                System.out.println("------------");
////                System.out.println(this.wordlist);
////                System.out.println("------------");
//            }
//
//
//            /**
//             * Stores the incoming array of Strings in a data structure that will make
//             * it convenient to find words.
//             *
//             * @param letterArray This array of length N^2 stores the contents of the
//             *                    game board in row-major order. Thus, index 0 stores the contents of board
//             *                    position (0,0) and index length-1 stores the contents of board position
//             *                    (N-1,N-1). Note that the board must be square and that the strings inside
//             *                    may be longer than one character.
//             * @throws IllegalArgumentException if letterArray is null, or is  not
//             *                                  square.
//             */
//            @Override
//            public void setBoard(String[] letterArray)
//            {
//                if (letterArray == null || Math.pow((int) Math.sqrt(letterArray.length), 2) != letterArray.length)
//                {
//                    throw new IllegalArgumentException();
//                }
//
//                this.boardSize = (int) Math.sqrt(letterArray.length);
//                this.letterArray = new char[boardSize][boardSize];
//
//                for (int i = 0; i < this.boardSize; i++) // x
//                {
//                    for (int j = 0; j < this.boardSize; j++) // y
//                    {
//                        this.letterArray[i][j] = letterArray[i + j * this.boardSize].charAt(0);
//                    }
//                }
//            }
//
//            /**
//             * Creates a String representation of the board, suitable for printing to
//             * standard out. Note that this method can always be called since
//             * implementing classes should have a default board.
//             */
//            @Override
//            public String getBoard()
//            {
//                if (this.letterArray == null)
//                {
//                    this.letterArray = new char[5][5];
//
//                    for (char[] row : letterArray)
//                    {
//                        Arrays.fill(row, '0');
//                    }
//
//                    return getBoard();
//                }
//                else
//                {
//                    String board = "";
//
//                    for (char[] row : this.letterArray)
//                    {
//                        for (char letter : row)
//                        {
//                            board += letter;
//                        }
//
//                        board += "\n";
//                    }
//
//                    return board;
//                }
//            }
//
//            /**
//             * Retrieves all scorable words on the game board, according to the stated game
//             * rules.
//             *
//             * @param minimumWordLength The minimum allowed length (i.e., number of
//             *                          characters) for any word found on the board.
//             * @return java.util.SortedSet which contains all the words of minimum length
//             * found on the game board and in the lexicon.
//             * @throws IllegalArgumentException if minimumWordLength < 1
//             * @throws IllegalStateException    if loadLexicon has not been called.
//             */
//            @Override
//            public SortedSet<String> getAllScorableWords(int minimumWordLength)
//            {
//                if (minimumWordLength < 1)
//                {
//                    throw new IllegalArgumentException();
//                }
//                else if (!this.wordListLoaded)
//                {
//                    throw new IllegalStateException();
//                }
//
//                SortedSet<String> scorableWords = new TreeSet<>();
//
//                for (String word : wordlist)
//                {
//                    if (word.length() >= minimumWordLength && !isOnBoard(word).isEmpty())
//                    {
//                        scorableWords.add(word);
//                    }
//                }
//
//                return scorableWords;
//            }
//
//            /**
//             * Computes the cumulative score for the scorable words in the given set.
//             * To be scorable, a word must (1) have at least the minimum number of characters,
//             * (2) be in the lexicon, and (3) be on the board. Each scorable word is
//             * awarded one point for the minimum number of characters, and one point for
//             * each character beyond the minimum number.
//             *
//             * @param words             The set of words that are to be scored.
//             * @param minimumWordLength The minimum number of characters required per word
//             * @return the cumulative score of all scorable words in the set
//             * @throws IllegalArgumentException if minimumWordLength < 1
//             * @throws IllegalStateException    if loadLexicon has not been called.
//             */
//            @Override
//            public int getScoreForWords(SortedSet<String> words, int minimumWordLength)
//            {
//                if (minimumWordLength < 1)
//                {
//                    throw new IllegalArgumentException();
//                }
//                else if (!wordListLoaded)
//                {
//                    throw new IllegalStateException();
//                }
//
//                int points = 0;
//
//                for (String word : words)
//                {
//                    if (word.length() >= minimumWordLength && isValidWord(word) && isOnBoard(word).get(0) != -1)
//                    {
//                        points += word.length() - minimumWordLength + 1;
//                    }
//                }
//
//                return points;
//            }
//
//            /**
//             * Determines if the given word is in the lexicon.
//             *
//             * @param wordToCheck The word to validate
//             * @return true if wordToCheck appears in lexicon, false otherwise.
//             * @throws IllegalArgumentException if wordToCheck is null.
//             * @throws IllegalStateException    if loadLexicon has not been called.
//             */
//            @Override
//            public boolean isValidWord(String wordToCheck)
//            {
//                if (wordToCheck == null)
//                {
//                    throw new IllegalArgumentException();
//                }
//                else if (!this.wordListLoaded)
//                {
//                    throw new IllegalStateException();
//                }
//
//                return wordlist.contains(wordToCheck);
//            }
//
//            /**
//             * Determines if there is at least one word in the lexicon with the
//             * given prefix.
//             *
//             * @param prefixToCheck The prefix to validate
//             * @return true if prefixToCheck appears in lexicon, false otherwise.
//             * @throws IllegalArgumentException if prefixToCheck is null.
//             * @throws IllegalStateException    if loadLexicon has not been called.
//             */
//            @Override
//            public boolean isValidPrefix(String prefixToCheck)
//            {
//                for (String word : wordlist)
//                {
//                    if (word.startsWith(prefixToCheck))
//                    {
//                        return true;
//                    }
//                }
//
//                return false;
//            }
//
//            /**
//             * Determines if the given word is in on the game board. If so, it returns
//             * the path that makes up the word.
//             *
//             * @param wordToCheck The word to validate
//             * @return java.util.List containing java.lang.Integer objects with the path
//             * that makes up the word on the game board. If word is not on the game
//             * board, return an empty list. Positions on the board are numbered from zero
//             * top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
//             * board, the upper left position is numbered 0 and the lower right position
//             * is numbered N^2 - 1.
//             * @throws IllegalArgumentException if wordToCheck is null.
//             * @throws IllegalStateException    if loadLexicon has not been called.
//             */
//            @Override
//            public List<Integer> isOnBoard(String wordToCheck)
//            {
//                List<Integer> discovered = new ArrayList<>();
//
//                int[] word = new int[wordToCheck.length()];
//                Arrays.fill(word, -1);
//
////                System.out.println("WORD: " + word.length);
////                System.out.println("WORD: " + wordToCheck.length());
//
//                int index = 0;
//
//                while (index < wordToCheck.length())
//                {
//                    int letterSquare;
//
//                    if (index == 0)
//                    {
//                        letterSquare = toInt(find(wordToCheck.charAt(index), -1, -1, discovered));
//                    }
//                    else
//                    {
//                        letterSquare = toInt(find(wordToCheck.charAt(index), word[index - 1] % 4, word[index - 1] / 4, discovered));
//                    }
//
//                    if (letterSquare >= 0) // letter found
//                    {
//                        discovered.add(letterSquare);
//                        word[index] = letterSquare;
//                        index++;
//                    }
//                    else // no letter found
//                    {
//                        if (index == 0)
//                        {
//                            return new ArrayList<>();
//                        }
//                        else
//                        {
//                            word[index - 1] = -1;
//                            index--;
//                        }
//
////                        discovered.removeLast();
////                        word[index] = -1;
//                    }
//                }
//
//                return Arrays.stream(word).boxed().collect(Collectors.toList());
//            }
//
//            private int[] find(char charToFind, int cellRow, int cellCol, List<Integer> visitedCells)
//            {
//                int[] bounds = new int[4]; // row start, end; column start, end
//                // find iteration bounds
//                if (cellRow == -1)
//                {
//                    bounds[0] = bounds[2] = 0;
//                    bounds[1] = bounds[3] = this.boardSize - 1;
//                }
//                else
//                {
//                    bounds[0] = Math.max(0, cellRow - 1);
//                    bounds[1] = Math.min(cellRow + 1, this.boardSize - 1);
//                    bounds[2] = Math.max(0, cellCol - 1);
//                    bounds[3] = Math.min(cellCol + 1, this.boardSize - 1);
//                }
//
//                for (int i = bounds[0]; i <= bounds[1]; i++)
//                {
//                    for (int j = bounds[2]; j <= bounds[3]; j++)
//                    {
//                        if (i == cellRow && j == cellCol)
//                        {
//                            continue;
//                        }
//
////                        int test = i + j * 4;
//
//                        if (!visitedCells.contains(i + j * 4) && letterArray[i][j] == charToFind)
//                        {
//                            return new int[]{i, j};
//                        }
//                    }
//                }
//
//                return new int[]{-1, -1};
//            }
//
//            private int toInt(int[] index)
//            {
//                return index[0] + index[1] * this.boardSize;
//            }
//
//            private int[] toIndex(int cell)
//            {
//                return new int[]{cell % this.boardSize, cell / this.boardSize};
//            }
//        }
//
//                ;
//    }
//}