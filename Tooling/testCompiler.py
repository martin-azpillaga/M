from glob import glob
from subprocess import *

def main():
    for filename in glob('../**/*.md', recursive=True):
        code = []
        recording = False
        language = "undefined"
        record = []
        for line in open(filename, 'r'):

            if line == '~~~\n':
                code.append((language, record))
                record = []
                recording = False

            elif line.startswith('~~~'):
                language = line.split(' ')[-1].replace('\n','')
                recording = True
            
            else:
                if recording:
                    record.append(line)
        if len(code) > 0:
            print (filename)
            check (code)

def check(code):
    for (i, (language, record)) in enumerate(code):
        if language == 'text':
            debug = open('test.text', 'w')
            debug.writelines(record)
            debug.flush()
            result = run(['java', '-jar', 'm.jar', 'test.text'], stdout=PIPE, stderr=PIPE)
            stdout = result.stdout.decode('utf-8')
            stderr = result.stderr.decode('utf-8')

            if i+1 < len(code):
                
                if code[i+1][0] == 'error':
                    error = ''.join(code[i+1][1])
                    if (stderr == error):
                        print ("✔ check with error: OK")
                    else:
                        print ("✘ check with error: Errors don't match")

                elif code[i+1][0] == 'out':
                    out = ''.join(code[i+1][1])
                    if (stdout == out):
                        print ("✔ check with out: OK")
                    else:
                        print ("✘ check with out: Outputs don't match")

                else:
                    if (stderr == ""):
                        print ("✔ text block: OK")
                    else:
                        print ("✘ text block: Unexpected error")
            else:
                if (stderr == ""):
                    print ("✔ text block: OK")
                else:
                    print ("✘ text block: Unexpected error")

main()