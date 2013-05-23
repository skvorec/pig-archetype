pig-archetype
=============

This is maven archetype that creates project for testing pig scripts.

It consist of three modules: a module with assert class, a module with maven archetype and a module with maven plugin for initialization project. 
In fact, archetype should not contains assert module, but I think using separate module is the best way in this case.

Archetype has three non standart parameters:

-Dscript - a path to script that will be tested

-Dinput - a path to input data for script

-Doutput - a path to output dir with expected script output data 

To copy all these files to project structure, use following option of archetype plugin: -Dgoals=org.pigarchetype:init-maven-plugin:pig-init

A generated project consist of one test file - PigTest.java that launches PigServer using ExecType.Local mode, runs script and compares actual output with expected.



Assumptions:

-- a tested script has parameters with names $data and $output for input file and output dir

-- pig script does not register any jars


Known problems:
--[SOLVED] in win7 PigServer tries launch chmod command and test fails
Solution: download cygwin chmod.exe from here: http://javaprotlib.sourceforge.net/packages/io/howtofixhadoop.html