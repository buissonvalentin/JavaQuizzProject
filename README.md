# JavaQuizzProject

Welcome to the JavaQuizzProject wiki!

This project was made for the Java class final project.

This programm offer a few fonctionnalities:

The quizz itself which is 10 random questions. 10 questions are hard coded, and some are loaded from a database. After the quizz, the user can save his result with his name in the database. THe last 5 results are displayed on the main window of the application. After each question, the user can see the correct answer if he did not find it himself.

The second part is the possibility to create you own question, which you can save on the database, and will then be use during the quizz.

There are 4 differents type of question:

    Text : the user type in the answer in text field. (1 correct answer)
    Combo : the user choose between 4 (or less) propositions. (1 correct answer)
    Radio : the user choose between 4 (or less) propositions. (1 correct answer)
    Multiple : the user choose between 4 (or less) propositions. (1 or more correct answer)
    
  
 Set up the project :
 
 To import a project in Eclipse :
 
   Put all source files into one directory named after your project.  You can keep this directory in your workspace or it can be somewhere else.
    Start a new project in eclipse and name it using that same project name.
    Uncheck the "use default location" box and find the directory where your project is unless your project is already in the workspace - then you must not uncheck the "use default location" box
    Click 'next'.


For the application to work correctly, you will need to add the databse driver to the project.

In Eclipse, Package Explorer: right click on your project 
> Properties 
> Java Build Path 
> Libraries tab
> Add external JARs

Then choose the "mysql-connector-java-5.1.44-bin.jar" file that should be in the zip that you dowloaded.


