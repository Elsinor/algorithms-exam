import subprocess, os, sys

CLASS_PATH = {
    'Ex1': 'fabrizio.armango.Ex1',
    'Ex2': 'fabrizio.armango.Ex2',
    'Ex3': 'fabrizio.armango.Ex3',
    'Ex4': 'fabrizio.armango.Ex4',
}
CLASS_DEP = {
    'Ex1': 'src/fabrizio/armango/ex1/*.java src/fabrizio/armango/Ex1.java',
    'Ex2': 'src/fabrizio/armango/Ex2.java',
    'Ex3': 'src/fabrizio/armango/Ex2.java src/fabrizio/armango/ex3/*.java src/fabrizio/armango/Ex3.java',
    'Ex4': 'src/fabrizio/armango/ex4/*.java src/fabrizio/armango/Ex4.java',
}

def ex(cmd):
    print(cmd)
    return subprocess.call(cmd, shell=True) == 0

def compile(c):
    return ex('javac -d classes -cp "/usr/local/algs4/algs4.jar" ' + c + ' src/Main.java')

def run(c, args=""):
    return ex(
        'jar --create --file main.jar --manifest=MANIFEST.MF -C classes/ .' +
        '&& ' +
        'java -jar main.jar ' + c + ' ' + args
    )

def printClassList():
    print(*CLASS_PATH.keys(), sep='\n')

def main():
    if len(sys.argv) > 1:
        p1 = sys.argv[1]
        if p1 == 'list':
            printClassList()
        elif p1 in CLASS_PATH.keys():
            c = CLASS_PATH[p1]
            d = CLASS_DEP[p1]
            if compile(d):
                args = " ".join(sys.argv[2:])
                run(c, args=args)
            else:
                print("Compilation failed, please check errors.")
        else:
            print("Command not found.")
    else:
        print("run with a <classname> or run with list")

if __name__ == "__main__":
    main()
