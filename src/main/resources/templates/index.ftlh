<!DOCTYPE html>
<html lang="en">
<head></head>
<body>
<form>
  <button type="submit" formaction="/mint" formmethod="post">Mint Nft</button>
</form>
<table>
  <tr>
    <th>Name</th>
    <th>Serial</th>
    <th>Metadata</th>
  </tr>
    <#list nfts as nft>
      <tr>
        <td>${nft.name()}</td>
        <td>${nft.serial()}</td>
        <td>${nft.metadata()}</td>
      </tr>
    </#list>
</table>
</body>
</html>