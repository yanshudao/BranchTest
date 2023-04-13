package io.renren.modules.subject.entity.request;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class OrderbillChildren {
    @XStreamImplicit(itemFieldName="book")
    List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

}
