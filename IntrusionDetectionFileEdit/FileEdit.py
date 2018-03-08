infile = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup.data.txt'
outfileNormal = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_normal.data.txt'
outfileSmurf = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_smurf.data.txt'
outfileNeptune = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_neptune.data.txt'
outfileBack = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_back.data.txt'
outfileOverflow = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_buffer_overflow.data.txt'
outfileFtp = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_ftp_write.data.txt'
outfilePasswd = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_guess_passwd.data.txt'
outfileImap = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_imap.data.txt'
outfileIpsweep = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_ipsweep.data.txt'
outfileLand = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_land.data.txt'
outfileLoad = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_loadmodule.data.txt'
outfileMulti = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_multihop.data.txt'
outfileNmap = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_nmap.data.txt'
outfilePerl = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_perl.data.txt'
outfilePhf = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_phf.data.txt'
outfilePod = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_pod.data.txt'
outfilePortsweep = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_portsweep.data.txt'
outfileRoot = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_rootkit.data.txt'
outfileSatan = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_satan.data.txt'
outfileSpy = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_spy.data.txt'
outfileTeardrop = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_teardrop.data.txt'
outfileWclient = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_warezclient.data.txt'
outfileWmaster = 'C:\\Users\\Jimmy\\Documents\\Brockport\\Intrusion Detection Project\\kddcup_warezmaster.data.txt'


with open(infile, 'rb') as readIn:
    lines = readIn.readlines()

with open(outfileNormal, 'wb') as output:
    for line in lines:
        if b'normal.' in line:
            output.write(line)

with open(outfileSmurf, 'wb') as output:
    for line in lines:
        if b'smurf.' in line:
            output.write(line)
            
with open(outfileNeptune, 'wb') as output:
    for line in lines:
        if b'neptune.' in line:
            output.write(line)
            
with open(outfileBack, 'wb') as output:
    for line in lines:
        if b'back.' in line:
            output.write(line)
            
with open(outfileOverflow, 'wb') as output:
    for line in lines:
        if b'buffer_overflow.' in line:
            output.write(line)
            
with open(outfileFtp, 'wb') as output:
    for line in lines:
        if b'ftp_write.' in line:
            output.write(line)
            
with open(outfilePasswd, 'wb') as output:
    for line in lines:
        if b'guess_passwd.' in line:
            output.write(line)
            
with open(outfileImap, 'wb') as output:
    for line in lines:
        if b'imap.' in line:
            output.write(line)
            
with open(outfileIpsweep, 'wb') as output:
    for line in lines:
        if b'ipsweep.' in line:
            output.write(line)
            
with open(outfileLand, 'wb') as output:
    for line in lines:
        if b'land.' in line:
            output.write(line)

with open(outfileLoad, 'wb') as output:
    for line in lines:
        if b'loadmodule.' in line:
            output.write(line)

with open(outfileMulti, 'wb') as output:
    for line in lines:
        if b'multihop.' in line:
            output.write(line)

with open(outfileNmap, 'wb') as output:
    for line in lines:
        if b'nmap.' in line:
            output.write(line)

with open(outfilePerl, 'wb') as output:
    for line in lines:
        if b'perl.' in line:
            output.write(line)

with open(outfilePhf, 'wb') as output:
    for line in lines:
        if b'phf.' in line:
            output.write(line)

with open(outfilePod, 'wb') as output:
    for line in lines:
        if b'pod.' in line:
            output.write(line)
            
with open(outfilePortsweep, 'wb') as output:
    for line in lines:
        if b'portsweep.' in line:
            output.write(line)

with open(outfileRoot, 'wb') as output:
    for line in lines:
        if b'rootkit.' in line:
            output.write(line)

with open(outfileSatan, 'wb') as output:
    for line in lines:
        if b'satan.' in line:
            output.write(line)
            
with open(outfileSpy, 'wb') as output:
    for line in lines:
        if b'spy.' in line:
            output.write(line)
            
with open(outfileTeardrop, 'wb') as output:
    for line in lines:
        if b'teardrop.' in line:
            output.write(line)
            
with open(outfileWclient, 'wb') as output:
    for line in lines:
        if b'warezclient.' in line:
            output.write(line)
            
with open(outfileWmaster, 'wb') as output:
    for line in lines:
        if b'warezmaster.' in line:
            output.write(line)