<html>
<head><title>Twitter Stream Control Page</title></head>
<h1>Feeder Control Page</h1>
<hr>
<h2>Twitter Stream</h2>
<body>
<form name="frm" method="GET" action="/twitterStream">
 <input type="hidden" name="action" value="start"/>
 <input type="submit" name="dummyAction" value="Start Twitter Steam" onclick="{document.frm.submit();}" />
</form>
</html>
<form name="frm" method="GET" action="/twitterStream">
 <input type="hidden" name="action" value="stop"/>
 <input type="submit" name="dummyAction" value="Stop Twitter Steam" onclick="{document.frm.submit();}" />
</form>
<form name="frm" method="GET" action="/twitterStream">
 <input type="hidden" name="action" value="show"/>
 <input type="submit" name="dummyAction" value="Show Twitter Steam Filters" onclick="{document.frm.submit();}" />
</form>
<form name="frm" method="GET" action="/twitterStream">
 <input type="hidden" name="keywords" value="#TESLA" id="keywords">
 <input type="hidden" name="action" value="update"/>
 <input type="submit" name="dummyAction" value="Stream #TESLA" onclick="{document.frm.submit();}" />
</form>
<form name="frm" method="GET" action="/twitterStream">
 <input type="hidden" name="keywords" value="#HSBC" id="keywords">
 <input type="hidden" name="action" value="update"/>
 <input type="submit" name="dummyAction" value="Stream #HSBC" onclick="{document.frm.submit();}" />
</form>
<form name="frm" method="GET" action="/demo">
 <input type="submit" name="dummyAction" value="demo" onclick="{document.frm.submit();}" />
</form>
</body>
</html>