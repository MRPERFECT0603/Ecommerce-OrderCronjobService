package com.eccomerce.ecommercebackend.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderReadRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderReadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PendingOrder> findPendingOrders(int limit) {
        return jdbcTemplate.query(
                """
                SELECT id, user_id, order_value
                FROM orders
                WHERE status = 'Pending'
                LIMIT ?
                """,
                (rs, rowNum) -> new PendingOrder(
                        rs.getString("id"),
                        rs.getString("user_id"),
                        rs.getDouble("order_value")
                ),
                limit
        );
    }

    @Data
    @AllArgsConstructor
    public static class PendingOrder {
        private String orderId;
        private String userId;
        private Double orderValue;
    }
}