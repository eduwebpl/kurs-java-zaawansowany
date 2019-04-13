### Project: Directory comparator 

#### Interface: Not important

#### Requirements:
* compare files in given two directories (ignore empty dirs)
* find the missing files
* find different files
 
#### Nice to have:
* if files are different show their md5 checksums
* allow the comparison of nested directories

#### Suggested implementation
* compute md5 checksums for all files in dir1/dir2 (do it concurrently)
* compare checksums for corresponding files
* find the missing files