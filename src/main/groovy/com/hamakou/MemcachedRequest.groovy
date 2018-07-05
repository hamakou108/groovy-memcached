package com.hamakou

/**
 * MemcachedRequest provides methods to parse a memcached request message and saves its
 * information as instance variables.
 */
class MemcachedRequest implements Request {
  /**
   * The raw request message.
   */
  String msg

  /**
   * Saves and parses a request messages.
   *
   * @param msg the raw request message
   */
  def MemcachedRequest(String msg) {
      this.msg = msg
      this.parseMsg()
  }

  /**
   * Parses a request message and saves its information.
   */
  def parseMsg() {
  }
}
