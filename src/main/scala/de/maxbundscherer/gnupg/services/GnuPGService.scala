package de.maxbundscherer.gnupg.services

import de.maxbundscherer.gnupg.utils.Configuration
import org.slf4j.Logger

class GnuPGService()(implicit log: Logger) extends Configuration {

  import scala.util.{ Failure, Success, Try }
  import scala.language.postfixOps
  import scala.sys.process._

  log.info("GnuPGService started")

  private def formOutputToHtml(input: String): String = input.replaceAll("\n", "<br>")

  private def shellCmdWrapper(cmd: String): String =
    Try {
      cmd !!
    } match {
      case Failure(exception) =>
        val errorMsg = s"Cmd failed ($cmd) (${exception.getLocalizedMessage})"
        log.warn(errorMsg)
        errorMsg
      case Success(response) => response
    }

  private val workDir = Config.GnuPGService.workDir

  def getWorkDirFiles: String =
    this.formOutputToHtml(this.shellCmdWrapper(cmd = "ls -al " + workDir)) + "\n\n"

  def getPublicKeys: String =
    this.formOutputToHtml(input = this.shellCmdWrapper(cmd = "gpg --list-keys"))

}