# METHOD USED
# Brody Clark
# CSCI102 - Section C
# Week 12 - Part A

def PrintOutput(string):
     print('OUTPUT', string)

def LoadFile(file_name):
    with open(file_name, 'r') as read_file:
        lines = read_file.readlines()
    print(lines)
