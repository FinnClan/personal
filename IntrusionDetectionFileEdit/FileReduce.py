infile = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup.data.txt'
outfile = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_tempout.data.txt'
with open(infile, 'r+') as readIn:
    lines = readIn.readlines()
    
    with open(outfile, 'w+') as output:
        for line in lines:
            if 'normal.' not in line and 'neptune.' not in line and 'back.' not in line and 'ftp_write.' not in line and 'imap.' not in line and 'ipsweep.' not in line and 'multihop.' not in line and 'nmap.' not in line and 'portsweep.' not in line and 'smurf.' not in line and 'satan.' not in line and 'rootkit.' not in line and 'pod.' not in line and 'teardrop.' not in line and 'warezmaster.' not in line and 'warezclient.' not in line and 'phf.' not in line and 'perl.' not in line and 'guess_passwd.' not in line and 'land.' not in line and 'buffer_overflow.' not in line :
                output.write(line)