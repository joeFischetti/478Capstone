# 478Capstone
Team project for CSC478 Capstone @ UIS
Pesavento-test edit


The Gradebook class is the main controller class file for the entire program.  The additional classes are either used for data, or views.  This is an MVC design.

Within the Gradebook class there are inner classes, many of which implement ActionListeners.  Each of the other panel classes has setter methods for the buttons.  This allows all of the logic code to live in the Gradebook class, while view related code is generally placed in the view classes.  After each panel is created, it's setter methods for the JButtons are called to set the ActionListener for each button.

#Manage Students:
The manage students page consists of a list of students on the left side, with detailed student info on the right side.  The Student list is a DefaultListModel of type Student.  Initially, an SQL query is used that returns the ID number, first name, and last name of each student, and adds each student to the list model.  When a student is selected from the pane and the 'load more' button is selected, the ID number of the selected student is used to query the database for all of the student information. That information is then placed in the right side of the windows.

The add student button opens a new window with blank fields.  After the submit button is pressed, a new student is created with the information provided, and that student object's data is then placed in the database.

Modify student data will work similar.  A new (editable) window will open which will allow the user to change any of the data except the date of birth and the student ID.

Deleting a student will be done by setting the "active" flag in the database for that student to 0.  This ensures that should future functionality require other tables to link to the student table, foreign keys won't go missing.
