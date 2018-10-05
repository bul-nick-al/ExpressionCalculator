# HA-1: Expression calculator

## by Nikolay Buldakov, BS3-SE



#### Description.


This assignment implements expression calculator in java. It parses a

mathematical expression in text representation and builds an expression tree.

In order to calculate the expression, it then traverses the tree.


#### Location of input and output.



The input data for the program is in the `in.txt` file. The output will be saved

to the `out.txt`. If `out.txt` does not exist, a new one will be created.


#### Requirements.



To run the program you will need `Java JDK` installed on your computer. If you don't have it,

please refer to [this link](https://apple.stackexchange.com/questions/276772/how-to-install-java-using-terminal). Or just google it yourself :)



#### Running the program.



In the folder you can find the script for running the program: `script_for_run.sh`. If you have problems

with permission for running the script, you may use a workaround, just run this in the terminal:

```
chmod +x script_for_run.sh
./script_for_run.sh
```



You can also compile and run the program yourself by executing the following commands:

```
cd src
javac Main.java
java Main
cd ..
```

#### Tets.

The tests can be found in the src folder. The file name is `ParserTest.java`

#### Note!

For this project variables of type `long` are used, that is why some combinations of values may result in an overflow

That's it, enjoy:)
