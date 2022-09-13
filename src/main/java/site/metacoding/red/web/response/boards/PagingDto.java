package site.metacoding.red.web.response.boards;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PagingDto {
	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private boolean isFirst;
	private boolean isLast;

	private Integer startPageNum;
	private Integer lastPageNum;
	private Integer blockPage;
	private Integer blockPageCount;
	
	private String keyword;
	
	public PagingDto(Integer totalCount, Integer totalPage, Integer currentPage,boolean isFirst, boolean isLast) {
		this.totalCount=totalCount;
		this.totalPage=totalPage;
		this.currentPage=currentPage;
		this.isFirst=isFirst;
		this.isLast=isLast;
	}
	
	
}
