import urllib.request
import re


url_to_download = "http://www.cvedetails.com/vulnerability-list/vendor_id-1224/product_id-15031/Google-Chrome.html"

req = urllib.request.Request(url_to_download)
page_source_raw = urllib.request.urlopen(req)
page_source = page_source_raw.read().decode("utf-8")

#fetch url source
print(page_source)
#applyin regular expressions
regex = re.compile("<a href=\"(.*?)\" title=\"(.*?)\">(.*?)<\/a>")
raw_content = regex.findall(page_source)
for f in raw_content:
    print(f)
    