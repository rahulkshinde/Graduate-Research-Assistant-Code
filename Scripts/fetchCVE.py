# fetch all the links in CVE site
#!/usr/local/bin/python
import urlparse
import urllib
from bs4 import BeautifulSoup

weburl = "http://www.cvedetails.com/vulnerability-list/vendor_id-1224/product_id-15031/Google-Chrome.html"

weburls = [weburl]


while len(weburls) > 0:
	try:
		htmltext = urllib.urlopen(weburls[0]).read()
	except:
		print weburls[0]
	soup = BeautifulSoup(htmltext)

	weburls.pop(0)

	for t in soup.findAll('a', href=True):
		t['href'] = urlparse.urljoin(weburl,t['href'])
		print t['href']
