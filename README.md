# soap_api_automation

Sample project to covert celcius to far

if you want to  use user name and password for requests use this in you request xml.You can simply get this header
by right clicking the request from soap UI.

<env:Header>
<wsse:Security env:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
    <wsse:UsernameToken wsu:Id="your token">
        <wsse:Username>${USERNAME}</wsse:Username>
        <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">${PASSWORD}</wsse:Password>
    </wsse:UsernameToken>
</wsse:Security>
</env:Header>
