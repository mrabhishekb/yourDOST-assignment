###Second Largest Unique Number Finder

##Description

This Python program takes a list of integers from the user and returns the second largest unique number in the list. The program first removes all duplicate values and then finds the second largest among the remaining numbers. If there is no second largest unique number, it returns -1.

#Example 1:
  Input: 5 3 9 3 5 7
  Unique numbers: 9, 7
  Second largest unique number: 7

#Example 2:
  Input: 7 7 5
  Unique numbers: 5
  Second largest unique number: -1

#Example #:
  Input: 5 5 5
  Unique numbers: None
  Second largest unique number: -1
  
##Solution Approach

#The program uses two main functions:

  #remove_duplicates(nums):
  Removes all numbers that appear more than once using a dictionary to count occurrences.
  
  #get_second_largest_num(nums):
  Finds the second largest number from the list of unique numbers.

Iterates through the list while keeping track of the largest and second largest numbers.

The program loops until the user chooses to exit.

##Time and Space Complexity

#remove_duplicates(nums)

  Time Complexity: O(n) – iterates over the list twice (once for counting, once for filtering)
  
  Space Complexity: O(n) – dictionary stores counts of all numbers

#get_second_largest_num(nums)

  Time Complexity: O(n) – single pass to find the two largest numbers
  
  Space Complexity: O(1) – only two variables for tracking largest values

#Overall Complexity

  Time Complexity: O(n)
  
  Space Complexity: O(n)
