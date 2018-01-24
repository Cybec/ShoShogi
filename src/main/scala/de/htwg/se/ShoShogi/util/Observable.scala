package de.htwg.se.ShoShogi.util

/**
  * Observer for Classes to be notified
  */
trait Observer {
  def update(): Unit
}

/**
  * Class to notify/add/remove Classes with Observer
  */
class Observable {
  /**
    * Vector of Subscribers to be notified
    */
  var subscribers: Vector[Observer] = Vector()

  /**
    * Add new Class to be notified
    *
    * @param s Class
    */
  def add(s: Observer): Unit = subscribers = subscribers :+ s

  /**
    * Remove Observed Class
    *
    * @param s Class
    */
  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  /**
    * Iterate through List of Classes and notify with update
    */
  def notifyObservers(): Unit = subscribers.foreach(o => o.update())
}
