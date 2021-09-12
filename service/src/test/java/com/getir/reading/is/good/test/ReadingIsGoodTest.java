package com.getir.reading.is.good.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.reading.is.good.book.model.Book;
import com.getir.reading.is.good.book.model.StockModel;
import com.getir.reading.is.good.common.response.Response;
import com.getir.reading.is.good.common.response.ResponseType;
import com.getir.reading.is.good.customer.model.Customer;
import com.getir.reading.is.good.order.model.Order;
import com.getir.reading.is.good.order.model.OrderStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingIsGoodTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static Customer customerToSave;

    private static Book bookToSave;

    private String savedCustomerId;

    private String savedBookId;
    private Book savedBook;

    private String savedOrderId;

    @BeforeAll
    public static void init() {
        customerToSave = new Customer();
        customerToSave.setEmail("test@email.com");
        bookToSave = new Book(10, "testBook", 10);
    }

    @Test
    public void testReadingIsGood() {
        testSaveCustomer();
        testSaveCustomerWithSameEmail();

        testSaveBook();
        testGetBookById();
        testAddStock();
        testDecreaseStock();

        testPlaceOrder();
        testUpdateOrderStatus();
    }

    private void testSaveCustomer() {
        Response response = restTemplate.postForEntity(getBasePath() + "customer", customerToSave, Response.class).getBody();
        assertThat(response.getResponseType()).isEqualTo(ResponseType.SUCCESS);
        assertThat(response.getData()).isNotNull();
        savedCustomerId = response.getData().toString();
    }

    private void testSaveCustomerWithSameEmail() {
        Response response = restTemplate.postForEntity(getBasePath() + "customer", customerToSave, Response.class).getBody();
        assertThat(response.getResponseType()).isEqualTo(ResponseType.ERROR);
        assertThat(response.getMessage()).isNotNull();
    }

    private void testSaveBook() {
        Response response = restTemplate.postForEntity(getBasePath() + "book", bookToSave, Response.class).getBody();
        assertThat(response.getResponseType()).isEqualTo(ResponseType.SUCCESS);
        assertThat(response.getData()).isNotNull();
        savedBookId = response.getData().toString();
    }

    private void testGetBookById() {
        Response response = restTemplate.getForEntity(getBasePath() + "book/by-id?id=" + savedBookId, Response.class).getBody();
        assertThat(response.getResponseType()).isEqualTo(ResponseType.SUCCESS);
        assertThat(response.getData()).isNotNull();
        savedBook = convertMapToObject((Map<String, Object>) response.getData(), Book.class);
    }

    private void testAddStock() {
        StockModel stockModel = new StockModel(savedBookId, 5);
        restTemplate.put(getBasePath() + "book/add-stock", stockModel);
        Response response = restTemplate.getForEntity(getBasePath() + "book/by-id?id=" + savedBookId, Response.class).getBody();
        Book addedBook = convertMapToObject((Map<String, Object>) response.getData(), Book.class);
        assertThat(addedBook.getNumberOfStock()).isEqualTo(15);
    }

    private void testDecreaseStock() {
        StockModel stockModel = new StockModel(savedBookId, 5);
        restTemplate.put(getBasePath() + "book/decrease-stock", stockModel);
        Response response = restTemplate.getForEntity(getBasePath() + "book/by-id?id=" + savedBookId, Response.class).getBody();
        Book decreasedBook = convertMapToObject((Map<String, Object>) response.getData(), Book.class);
        assertThat(decreasedBook.getNumberOfStock()).isEqualTo(10);
    }

    private void testPlaceOrder() {
        Order order = new Order();
        order.setQuantity(3);
        Book book = new Book();
        book.setId(savedBookId);
        Customer customer = new Customer();
        customer.setId(savedCustomerId);

        order.setOrderedBook(book);
        order.setCustomer(customer);

        Response response = restTemplate.postForEntity(getBasePath() + "order", order, Response.class).getBody();
        assertThat(response.getResponseType()).isEqualTo(ResponseType.SUCCESS);
        assertThat(response.getData()).isNotNull();

        savedOrderId = response.getData().toString();

        response = restTemplate.getForEntity(getBasePath() + "book/by-id?id=" + savedBookId, Response.class).getBody();
        Book decreasedBook = convertMapToObject((Map<String, Object>) response.getData(), Book.class);
        assertThat(decreasedBook.getNumberOfStock()).isEqualTo(7);

    }

    private void testUpdateOrderStatus() {
        restTemplate.put(getBasePath() + "order/" + savedOrderId + "/update-status", null);
        Response response = restTemplate.getForEntity(getBasePath() + "order/by-id?id=" + savedOrderId, Response.class).getBody();
        Order order = convertMapToObject((Map<String, Object>) response.getData(), Order.class);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.ON_DELIVERY);

        restTemplate.put(getBasePath() + "order/" + savedOrderId + "/update-status", null);
        response = restTemplate.getForEntity(getBasePath() + "order/by-id?id=" + savedOrderId, Response.class).getBody();
        order = convertMapToObject((Map<String, Object>) response.getData(), Order.class);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    private String getBasePath() {
        return "http://localhost:" + port + "/";
    }

    private <T> T convertMapToObject(Map<String, Object> map, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, tClass);
    }
}
