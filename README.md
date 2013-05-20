pig-archetype
=============

This is maven archetype that creates project for testing pig scripts.

It consist of two modules: a module with assert class and a module with maven archetype. In fact, archetype should not contains assert module, but I think using separate module is the best way in this case.
Archetype has three non standart parameters:

-Dscript - a path to script that will be tested

-Dinput - a path to input data for script

-Doutput - a path to output dir with expected script output data 


A generated project consist of one test file - PigTest.java that launches PigServer using ExecType.Local mode, runs script and compares actual output with expected.


Assumptions:

-- a tested script has parameters with names $data and $output for input file and output dir

-- pig script does not register any jars


Known problems:

-- a beauty way to copy script, input data and expected output data is not found yet. So, these files are copied during first test run. After the first run you should delete method firstRunInit();

-- in win7 PigServer tries launch chmod command and test fails