package dto;

import java.time.LocalDate;

/**
 * Encapsulates the filter parameters for the inventory report.
 * All fields are optional; a null/zero value means "no filter on this field".
 */
public class ReportFilter {

    private LocalDate dateFrom;   // filter products with created_at >= dateFrom
    private LocalDate dateTo;     // filter products with created_at <= dateTo
    private String keyword;       // partial match on SKU or name
    private long categoryId;      // 0 = all categories

    public ReportFilter() {}

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
