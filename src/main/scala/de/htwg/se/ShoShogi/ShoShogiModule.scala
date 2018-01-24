package de.htwg.se.ShoShogi

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.ShoShogi.controller.controllerComponent.{ ControllerInterface, controllerBaseImpl }
import de.htwg.se.ShoShogi.model.boardComponent.{ BoardInterface, boardBaseImpl }
import de.htwg.se.ShoShogi.model.fileIoComponent.{ FileIOInterface, fileIoJsonImpl }
import net.codingwell.scalaguice.ScalaModule

object ShoShogiModuleConf {
  val defaultBoardSize: Int = 9
  val smallBoardSize: Int = 6
  val tinyBoardSize: Int = 3

  val defaultBoard: String = "normal"
  val smallBoard: String = "small"
  val tinyBoard: String = "tiny"
}

class ShoShogiModule extends AbstractModule with ScalaModule {

  def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(ShoShogiModuleConf.defaultBoardSize)

    bind[BoardInterface].to[boardBaseImpl.BoardInj]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]
    //    bind[FileIOInterface].to[fileIoXmlImpl.FileIO]

    bind[BoardInterface].annotatedWithName(ShoShogiModuleConf.defaultBoard).toInstance(
      new boardBaseImpl.BoardInj(ShoShogiModuleConf.defaultBoardSize)
    )

    bind[BoardInterface].annotatedWithName(ShoShogiModuleConf.smallBoard).toInstance(
      new boardBaseImpl.BoardInj(ShoShogiModuleConf.smallBoardSize)
    )

    bind[BoardInterface].annotatedWithName(ShoShogiModuleConf.tinyBoard).toInstance(
      new boardBaseImpl.BoardInj(ShoShogiModuleConf.tinyBoardSize)
    )
  }

}
