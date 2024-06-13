# Student Attendance Desktop Application

## What this program is about
- This desktop application allows the user to store the information of a student based on their ID number, first and last names, and the college and program that the student is in.
- It has an 'admin mode' where the user can add or delete colleges, add or delete programs, delete students, save the progress of the data, or end the attendance session.
- The application creates a .pdf once the attendance session has been ended.
- Created using Java Swing and AWT, with assistance from FlatLaf for the final look.

## Java libraries used in this project (major libraries)
- Swing
- AWT (Abstract Window Toolkit)
- FlatLaf
- itextpdf
- net.URI (Uniform Resource Identifier)
- io (Input Output)
- time

## Problems encountered
- This is my first time working with Java in this scale. The syntax of Java, while not that hard to understand, was verbose.
- Swing was an entirely new library for me to learn. It was similar but significanlty different to TKinter from Python. I had to watch a full tutorial on Java Swing GUI first before starting this project.
- The biggest hurdle for me to overcome while working this project was how to place the widgets (buttons, labels, tables). I tried different Layout Managers, but the one that suited my best interests was GridBagLayout. However, GridBagLayout was not easy to learn. I got frustrated with it many times throughout the project, but eventually, I got the results that I wanted.
- I encountered a lot of problems while making this project that I won't be able to list all of it in this section. However, I will list all the references that I have used and the problem that it tried to solve.
- I worked on this project on and off for more than 3 months. My academics got in the way of finishing this project. I only managed to finish this project during the summer break.

## References that greatly helped in this project
- [Reference 1](https://stackoverflow.com/questions/4286759/how-to-show-hide-jpanels-in-a-jframe) (Hide panel in a JFrame)
- [Reference 2](https://stackoverflow.com/questions/9554636/the-use-of-multiple-jframes-good-or-bad-practice) (About using multiple JFrames)
- [Reference 3](https://stackoverflow.com/questions/46510996/how-can-i-change-the-jpanel-from-another-class) (Change JPanel from another class) (Andy McRae)
- [Reference 4](https://www.youtube.com/watch?v=nRJWltqX4UY) (Object passing)
- [Reference 5](https://www.youtube.com/watch?v=g2vDARb7gx) (GridBagLayout tutorial)
- [Reference 6](https://stackoverflow.com/questions/6364280/starting-gridbaglayout-from-top-left-corner-in-java-swing) (Put button on top left corner using GridBagLayout)
- [Reference 7](https://www.youtube.com/watch?v=pybU3E-eKfw) (Add JTable in Java Swing)
- [Reference 8](https://stackoverflow.com/questions/2501861/how-can-i-remove-a-jpanel-from-a-jframe) (Remove panel) (camickr's comment)
- [Reference 9](https://stackoverflow.com/questions/8796871/custom-title-on-joptionpane-message-dialog) (Put title in JOptionPane)
- [Reference 10](https://www.tutorialspoint.com/swingexamples/show_input_dialog_list.htm) (Show input dialog with list box)
- [Reference 11](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.htm) (All about ArrayList)
- [Reference 12](https://www.geeksforgeeks.org/arraylist-of-arraylist-in-java/) (2D ArrayList)
- [Reference 13](https://stackoverflow.com/questions/9265719/print-arraylist) (Print ArrayList)
- [Reference 14](https://stackoverflow.com/questions/2054347/show-jframe-but-not-show-title-bar-on-task-bar) (Difference between JWindow and JDialog)
- [Reference 15](https://stackoverflow.com/questions/1990817/how-to-make-a-jtable-non-editable) (Make JTable non-editable)
- [Reference 16](https://stackoverflow.com/questions/17641123/jtable-disable-user-column-dragging) (JTable disable user column dragging)
- [Reference 17](https://stackoverflow.com/questions/7433602/how-to-center-in-jtable-cell-a-value) (Center text in JTable) (camickr's comment)
- [Reference 18](https://www.youtube.com/watch?v=3LiSHPqbui) (Put buttons in JTable)
- [Reference 19](https://stackoverflow.com/questions/4577792/how-to-clear-jtable) (Remove the contents in a JTable)
- [Reference 20](https://www.youtube.com/watch?v=zKDmzKaAQro) (Read a .csv file in Java)
- [Reference 21](https://stackoverflow.com/questions/9750606/git-still-shows-files-as-modified-after-adding-to-gitignore) (Fix git showing files even after being in git ignore)
- [Reference 22](https://stackoverflow.com/questions/17067626/java-arraylist-and-exception-in-thread-awt-eventqueue-0-java-util-concurrentm#comment24682378_17067685) (Fix ConcurrentModificationException)
- [Reference 23](https://stackoverflow.com/questions/2601978/how-to-check-if-my-string-is-equal-to-null) (Check if string is equal to null)
- [Reference 24](https://stackoverflow.com/questions/11812142/how-to-get-data-from-a-jtable) (Get data from JTable)
- [Reference 25](https://www.tutorialspoint.com/how-can-we-remove-a-selected-row-from-a-jtable-in-java) (Delete row from a JTable)
- [Reference 26](https://www.youtube.com/watch?v=OsgX1grOJZA&t=153s) (Delete row from a JTable by selecting a row)
- [Reference 27](https://stackoverflow.com/questions/63464341/is-it-good-practice-to-try-with-resource-a-file-writer) (Try-with-resource a file writer)
- [Reference 28](https://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row)  (Get data from an entire row of a table) (Answer by Damilola Fagoyinbo)
- [Reference 29](https://stackoverflow.com/questions/4503656/java-removing-first-character-of-a-string) (Remove the first and last elements of a string using substrings)
- [Reference 30](https://www.javatpoint.com/java-get-current-date) (Get current date and time in Java) (Followed the first option)
- [Reference 31](https://www.geeksforgeeks.org/how-to-add-external-jar-file-to-an-intellij-idea-project/) (Put an external jar file into IntelliJ idea project)
- [Reference 32](https://www.youtube.com/watch?v=Zg7lS5sPN0M) (Generate PDF using itextpdf) (Also shows how to generate a table in PDF)
- [Reference 33](https://stackoverflow.com/questions/14373269/align-paragraph-at-the-center-of-the-page) (Put paragraph in center of page)
- [Reference 34](https://stackoverflow.com/questions/6922959/how-to-add-new-fonts-to-itext-using-java) (Set pagesize of pdf) (Techidiot's comment)
- [Reference 35](https://stackoverflow.com/questions/43425798/align-cell-in-itextpdf-java) (Align cell in table in itextpdf)
- [Reference 36](https://stackoverflow.com/questions/4577792/how-to-clear-jtable) (Delete contents of TableModel)
- [Reference 37](https://stackoverflow.com/questions/30603531/how-to-know-if-a-jtable-is-empty) (Check if JTable is empty)
- [Reference 38](https://www.youtube.com/watch?v=Dqlwr3uIeVM) (FlatLaf installation)
- [Reference 39](https://www.youtube.com/watch?v=52zIj2Yeqiw) (FlatLaf switch from light and dark mode @10:24)
- [Reference 40](https://stackoverflow.com/questions/685521/multiline-text-in-jlabel) (Multiline text using JLabel)
- [Reference 41](https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button) (Open a browser using a Java button)
- [Reference 42](https://stackoverflow.com/questions/8953266/replacement-for-java-net-url) (Replacement of java.net.URL)
- [Reference 43](https://www.youtube.com/watch?v=1izvzUbUF-c&t=215s) (Change Java icon)
- [Reference 44](https://mkyong.com/java/java-read-a-file-from-resources-folder/) (Read file outside of src folder; I just put everything in src but in a package)
- [Reference 45](https://stackoverflow.com/questions/3319479/can-i-git-commit-a-file-and-ignore-its-content-changes) (Git commit a file and then ignore its future changes)
- [Reference 46](https://stackoverflow.com/questions/12920652/git-update-index-assume-unchanged-returns-fatal-unable-to-mark-file) (Solution when the operation above returns fatal: Unable to mark file... error)
- [Reference 47](https://stackoverflow.com/questions/15745045/how-do-i-resolve-git-saying-commit-your-changes-or-stash-them-before-you-can-me)  (Fix Git saying "Commit your changes or stash them before you can merge" error after pulling from remote repository) (stdcall's answer - discard the local changes)
- [Reference 48](https://stackoverflow.com/questions/953972/java-jtable-setting-column-width) (Set table column width) (Harsha Basnayake's answer)
- [Reference 49](https://stackoverflow.com/questions/19158169/how-to-know-where-fileoutputstream-will-write-file) (Java know where FileOutputStream will write file)
