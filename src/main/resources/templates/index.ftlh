<!DOCTYPE html>
<html lang="en">
<head></head>
<body>
<form>
  <button type="submit" formaction="/mint" formmethod="post">Mint Nft</button>
  <button type="submit" formaction="/burnall" formmethod="post">Burn all Nfts</button>
  <button type="submit" formaction="/" formmethod="get">Refresh</button>
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