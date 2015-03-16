# Member Variables #
Private members should be in camelCase with an underscore following the name.

EX:
```
memberVariable_ = 4
```

# Functions #
Functions should be named using camelCase. The first word of the function should be lowercase, unless the function is a constructor.

EX:
```
functionName();
```

# Referencing Members and Functions #

References to functions and member variables should use the "this." qualifier, providing they are part of our source. References to external code should not use the qualifier.

EX:
```
this.myVariable_;
this.myFunction();
```

# Getters and Setters #

When you write getters and setters for a private member, the setter should have a "set" prefix and the accessor should have the same name as the private member, minus the trailing underscore. The "get" prefix should only be used on the accessor if the function performs some heavy lifting to get the value. (aka - if it's not just a private member being made public) That way, someone calling the accessor knows to cache the result in their code, instead of calling it over and over again.

**_I kind of just like it this way. I don't think this is how Java does it... But I secretly cringe every time I see this.getHeight()._**

# Documentation #

Our project uses the standard Javadoc formatting for comments. If you author a class, function, or test you should add ample comments within the body of the code and add a Javadoc comment above the class or function. You can automatically create a Javadoc comment template by selecting the function/class name and pressing Control-Option-J.