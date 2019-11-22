import glob
from subprocess import *

xtendFiles = 100
xtendLines = 10000
xtendBlanks = 1000
xtendComments = 0

def main():
    result = run(['scc', '..'], stdout=PIPE, stderr=PIPE)
    stdout = result.stdout.decode('utf-8')
    stderr = result.stderr.decode('utf-8')
    
    for line in stdout.split('\n'):
        if (line.startswith('Xtend')):
            print (line)

            entries = []
            for entry in line.split(' '):
                if (entry != ''):
                    entries.append(entry)
            
            assert int(entries[1]) <= xtendFiles, "too many files"
            assert int(entries[2]) <= xtendLines, "too many lines"
            assert int(entries[3]) <= xtendBlanks, "too many blanks"
            assert int(entries[4]) <= xtendComments, "too many comments"

if __name__ == "__main__":
    main()