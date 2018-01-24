package de.htwg.se.ShoShogi.controller.controllerComponent

object Simulator {
  val waitTime: Int = 1000
  var threadEnd: Boolean = false

  def start(controller: ControllerInterface, waitTime: Int = waitTime): Unit = {
    threadEnd = false
    val simuThread = new Thread(new Runnable {
      override def run() {
        controller.movePiece((6, 2), (6, 3))
        Thread.sleep(waitTime)
        controller.movePiece((7, 6), (7, 5))
        Thread.sleep(waitTime)
        controller.movePiece((1, 2), (1, 3))
        Thread.sleep(waitTime)
        controller.movePiece((3, 8), (2, 7))
        Thread.sleep(waitTime)
        controller.movePiece((5, 0), (6, 1))
        Thread.sleep(waitTime)
        controller.movePiece((7, 5), (7, 4))
        Thread.sleep(waitTime)
        controller.movePiece((7, 1), (6, 2))
        Thread.sleep(waitTime)
        controller.movePiece((2, 6), (2, 5))
        Thread.sleep(waitTime)
        controller.movePiece((6, 0), (7, 1))
        Thread.sleep(waitTime)
        controller.movePiece((1, 7), (6, 2))
        controller.promotePiece((6, 2))
        threadEnd = true
      }
    })
    simuThread.start()
  }
}
