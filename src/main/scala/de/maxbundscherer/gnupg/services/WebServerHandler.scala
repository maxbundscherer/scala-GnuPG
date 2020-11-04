package de.maxbundscherer.gnupg.services

import de.maxbundscherer.gnupg.utils.Configuration

import org.slf4j.Logger

private class WebServerHandler(gnuPGService: GnuPGService)(implicit log: Logger)
    extends Configuration {

  def home: String =
    this.Template.getTemplateHeader(
      metaTitle = "Home",
      title = "Welcome"
    ) +
    s"<p>" +
    s"" +
    s"<p>We still have things to do... (see README.md)</p>" +
    s"" +
    s"<ul>" +
    s"<li>Product-Name: '${Config.Global.productName}'</li>" +
    s"<li>Product-Version: '${de.maxbundscherer.gnupg.utils.BuildInfo.version}'</li>" +
    s"<li>Scala-Version: '${de.maxbundscherer.gnupg.utils.BuildInfo.scalaVersion}'</li>" +
    s"<li>SBT-Version: '${de.maxbundscherer.gnupg.utils.BuildInfo.sbtVersion}'</li>" +
    s"<li>Host:Port: '${Config.WebServer.host}:${Config.WebServer.port}'</li>" +
    s"<li>WorkDir: '${Config.GnuPGService.workDir}'</li>" +
    s"</ul>" +
    s"" +
    s"<ul>" +
    s"<li><a href='getWorkDirFiles'>Get work dir files</a></li>" +
    s"<li><a href='getPublicKeys'>Get public keys</a></li>" +
    s"<li><a href='getPrivateKeys'>Get private keys</a></li>" +
    s"<li><a href='writeTestFile'>Writes an test file to workDir</a></li>" +
    s"<li><a href='encryptMsg'>Encrypt msg</a></li>" +
    s"</ul>" +
    s"" +
    s"</p>" +
    this.Template.getTemplateFooter

  def getWorkDirFiles: String =
    this.Template.getTemplateHeader(
      metaTitle = "WorkDirFiles",
      title = "Work Dir Files"
    ) +
    s"<p>${this.gnuPGService.getWorkDirFiles}</p>" +
    this.Template.getTemplateFooter

  def getPublicKeys: String =
    this.Template.getTemplateHeader(
      metaTitle = "GetPublicKeys",
      title = "Public keys"
    ) +
    s"<p>${this.gnuPGService.getPublicKeys}</p>" +
    this.Template.getTemplateFooter

  def getPrivateKeys: String =
    this.Template.getTemplateHeader(
      metaTitle = "GetPrivateKeys",
      title = "Private keys"
    ) +
    s"<p>${this.gnuPGService.getPrivateKeys}</p>" +
    this.Template.getTemplateFooter

  def writeTestFile: String =
    this.Template.getTemplateHeader(
      metaTitle = "WriteTestFile",
      title = "Write Test File"
    ) +
    s"<p>${this.gnuPGService.writeTestFile}</p>" +
    this.Template.getTemplateFooter

  def encryptMsg: String =
    this.Template.getTemplateHeader(
      metaTitle = "EncryptMsg",
      title = "Encrypt Msg"
    ) +
    s"<p>tbd</p>" +
    this.Template.getTemplateFooter

  def encryptMsg2: String =
    this.Template.getTemplateHeader(
      metaTitle = "EncryptMsg2",
      title = "Encrypt Msg Finish"
    ) +
    s"<p>tbd</p>" +
    this.Template.getTemplateFooter

  private object Template {

    def getTemplateHeader(metaTitle: String, title: String): String =
      "<html>" +
      "<head>" +
      s"<title>$metaTitle - ${Config.Global.productName}</title>" +
      s"<style>" +
      "p, li, h1, h2, h3 {font-family: \"Verdana\"}" +
      s"</style>" +
      s"" +
      s"" +
      s"<script>" +
      s"function goBack() {" +
      s"window.history.back();" +
      s"}" +
      s"</script>" +
      s"" +
      "</head>" +
      "<body>" +
      s"<h1>${Config.Global.productName}</h1>" +
      s"<h2>$title</h2>" +
      s"<hr>"

    def getTemplateFooter: String =
      "<hr>" +
      this.getTemplateLink("Go back", "javascript:goBack()") +
      "<br />" +
      "" +
      this.getTemplateLink("Go to home", "/") +
      "</body>" +
      "</html>"

    def getTemplateLink(label: String, href: String): String = s"<a href='$href'>$label</a>"

  }

}
