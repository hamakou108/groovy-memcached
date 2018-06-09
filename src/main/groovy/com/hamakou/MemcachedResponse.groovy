package com.hamakou

import java.nio.charset.Charset

/**
 * MemcachedResponse provides methods to generate response message from request information.
 */
class MemcachedResponse implements Response {
  /**
   * temporary variable
   */
  String msg

  /**
   * Generate response information by request information.
   *
   * @param request request information parsed by request message
   */
  def MemcachedResponse(Request request) {
    this.msg = request.msg
  }

  /**
   * Generates a whole response messagea as a byte array.
   */
  def generateMsg() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    outputStream.setBytes(this.msg.getBytes(Charset.forName("UTF-8")))

    return outputStream.toByteArray();
  }
}
