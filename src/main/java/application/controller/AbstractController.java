package application.controller;

import com.server.ResponseData;
import com.server.controller.Controller;

public class AbstractController implements Controller {
    public byte[] get() {
        return new byte[0];
    }

    public byte[] head() {
        return new byte[0];
    }

    public byte[] post() {
        return new byte[0];
    }

    public byte[] put() {
        return new byte[0];
    }

    public byte[] patch() {
        return new byte[0];
    }

    public byte[] options() {
        return new byte[0];
    }

    public byte[] delete() {
        return new byte[0];
    }

    public void sendResponseData(ResponseData responseData) {

    }
}
