# SuperAwesome Code Challenge

Here is your SuperAwesome the code challenge!

## Our expectations
We expect *Production-Ready* code: code that's not only accomplishing the task, but it’s resilient, performing and maintainable by anybody in the team.
Details are important, and you should treat your submission as if it were a pull request ready to go live and serve millions of users.

A good submission includes a full suite of automated tests covering the edge cases, it handles exceptions, it's designed with separation of concerns in mind, and it uses resources (CPU, memory, disk...) with parsimony.
Last but not least, the code should be easy to read, with well named variables/functions/classes.

Check this article out if you want know more about what a good submission looks like
https://blog.superawesome.tv/2018/02/15/how-we-hire-engineers-to-build-kid-safe-technology/

We will evaluate your submission on:
* correctness
* completeness
* quality (see *production-ready* above)

## How much time do I have?
Usually this test is submitted within a couple of hours, but take how much time you need.
It's better to spend some more time on the test and submit a complete one, rather than rush it and submit a half-baked one (aka: we can't read your mind yet :)).

## What language should I use?
You choose the language. Our suggestion is to use the language you feel strongest at.

## The Task
Write a program that takes as argument the path to a file containing one word per line, groups the words that are anagrams to each other, and writes to the standard output each of these groups.
The groups should be separated by new lines and the words inside each group by commas.

#Assumption
* Assume that the words in the input file are ordered by size.
* Assume that the files may not fit in memory all at once.
* If you make other assumptions, make sure you write them down in a readme in your submission

#NOTES:
* We expect your code to compute the anagrams __without the help of any library__
* You are allowed to use any library for any other aspect functional to the task (e.g.: handling the CLI, testing, I/O)
* If CLI is not available in your development stack (e.g.: mobile) you can rely on automated tests and/or UI to feed your program with the input data and verify the output.
* The files provided in the `data` folder are just sample input data to help you reasoning about the problem
* The order of the groups in the output does not matter

Example:
```
command_to_run_your_program task/data/example.txt
```

Output:
```
abc,bac,cba
unf,fun
hello
```

# Once you're done..
Archive of your solution and send it via email to nick.yockney@superawesome.com
Do not submit binaries, libs (e.g.: node_modules), test results and other autogenerated content - you wouldn't include these in a pull request would you?

**NOTE**: it might happen that your email gets bounced because of the attachment, should that happen just upload your archived submission in google drive/dropbox/similar and share it with nick.yockney@superawesome.com

Last but not least: please keep the content of this code challenge confidential do not share it online not even in an anonymous form (aka: no github public repo please!)

## GOOD LUCK!


## Solution

# Further assumptions

* We assume minimum word's length of 2 letters
* We assume that the file has one word for each line only containing alphabet letters ([a-zA-Z]) with no spaces but can have empty lines.
* We assume that anagrams is case insensitive
* We assume that printing order of inner words' group does not matter, all though for testing we use a sorted set otherwise would be impossile to test the output.

# Algorithm

The input file is parsed line by line, then every word is converted to an integer based on mupliplication of single's letter representation as a prime factor. Now words can be grouped as anagram if they have same product. Time complexity is O(N x M) where M is average length of words in the file. Its range is from 0(N x 2) to O(N X Max(wordLength)). Using a sorted set only for testing avoids adding an extra O(log(N)) in time complexity

# Testing

Junit5 + Mockito
Mockito is used to mock System out and verify methods call

# Install and run

You need Java 8 and maven installed.
From root of the project run one of

``` 
mvn clean install
mvn clean package 
```

You can then run the application with `java -jar target/anagrams {path to your file}`
