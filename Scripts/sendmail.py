import smtplib

server = smtplib.SMTP('smtp.gmail.com',587)
server.login("XYZ", "(**********")
msg = "hello"
server.sendmail("rahulkshinde87@gmail.com", "rs1171@rit.edu", msg)