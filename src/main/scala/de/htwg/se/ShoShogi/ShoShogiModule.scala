package de.htwg.se.ShoShogi

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.ShoShogi.controller.controllerComponent.{ControllerInterface, controllerBaseImpl}
import de.htwg.se.ShoShogi.model.boardComponent.{BoardInterface, boardBaseImpl}
import de.htwg.se.ShoShogi.model.fileIoComponent.{FileIOInterface, fileIoJsonImpl}
import net.codingwell.scalaguice.ScalaModule

class ShoShogiModule extends AbstractModule with ScalaModule {

  val defaultSize: Int = 9

  def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)

    bind[BoardInterface].to[boardBaseImpl.BoardInj]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[FileIOInterface].to[fileIoJsonImpl.FileIO]

    bind[BoardInterface].annotatedWithName("normal").toInstance(new boardBaseImpl.BoardInj(9))
  }

}
