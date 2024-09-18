package com.designPatterns.builder;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryBuilder {
    public static class SearchQuery {
        private final String keyword;
        private final List<String> categories;
        private final Double minPrice;
        private final Double maxPrice;
        private final Boolean inStock;

        private SearchQuery(SearchQueryBuilder builder) {
            this.keyword = builder.keyword;
            this.categories = builder.categories;
            this.minPrice = builder.minPrice;
            this.maxPrice = builder.maxPrice;
            this.inStock = builder.inStock;
        }

        @Override
        public String toString() {
            return "SearchQuery{keyword='" + keyword + "', categories=" + categories +
                    ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", inStock=" + inStock + "}";
        }
    }

    private String keyword;
    private List<String> categories = new ArrayList<>();
    private Double minPrice;
    private Double maxPrice;
    private Boolean inStock;

    public SearchQueryBuilder setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public SearchQueryBuilder addCategory(String category) {
        this.categories.add(category);
        return this;
    }

    public SearchQueryBuilder setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public SearchQueryBuilder setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public SearchQueryBuilder setInStock(Boolean inStock) {
        this.inStock = inStock;
        return this;
    }

    public SearchQuery build() {
        return new SearchQuery(this);
    }

    public static void main(String[] args) {
        SearchQuery query1 = new SearchQueryBuilder()
                .setKeyword("laptop")
                .addCategory("electronics")
                .addCategory("computers")
                .setMinPrice(500.0)
                .setMaxPrice(2000.0)
                .setInStock(true)
                .build();

        SearchQuery query2 = new SearchQueryBuilder()
                .setKeyword("shoes")
                .addCategory("footwear")
                .setMaxPrice(100.0)
                .build();

        System.out.println(query1);
        System.out.println(query2);
    }
}