# Introduction #

The JPopUp class displays a message to the user (and receives an optional user response)


# Details #

## Informational Messages ##
This displays a pop-up box with just an OK button and an information icon.

**Usage:**
```
JPopUp msgBox = new JPopUp("This is a test");
msgBox.showMsg();
```

### Special Icon ###
To change the icon, use the overloaded showMsg() method indicating the message type.
Possible types are:
  * JOptionPane.INFORMATION\_MESSAGE
  * JOptionPane.ERROR\_MESSAGE
  * JOptionPane.PLAIN\_MESSAGE
  * JOptionPane.QUESTION\_MESSAGE
  * JOptionPane.WARNING\_MESSAGE

**Usage:**
```
JPopUp msgBox = new JPopUp("This is a warning");
msgBox.showMsg(JOptionPane.WARNING_MESSAGE);
```

## Dialog Messages ##
This displays a pop-up box with a button for each option passed in. The button clicked is returned by the function (unless the dialog was closed in which case NULL is returned)

**Usage:**
```
private static final String MAYBE = "Maybe";
private static final String NO = "No I'm not";
private static final String YES = "Yes I am";

String[] options = {YES,NO,MAYBE};

JPopUp dialogBox = new JPopUp("Are you sure?");
String ans = dialogBox.promptUser(options);

if(ans == MAYBE) 
  System.out.println("Maybe");
  
```