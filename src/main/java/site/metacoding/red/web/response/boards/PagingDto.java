package site.metacoding.red.web.response.boards;

import java.util.List;

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
	
	private List<MainDto> mainDto;
	
//  이거 왜 만듦?
//	public PagingDto(Integer totalCount, Integer totalPage, Integer currentPage,boolean isFirst, boolean isLast) {
//		this.totalCount=totalCount;
//		this.totalPage=totalPage;
//		this.currentPage=currentPage;
//		this.isFirst=isFirst;
//		this.isLast=isLast;
//	}
	public void dopaging() {
		blockPageCount = 5;
		startPageNum = ((currentPage / blockPageCount) * blockPageCount) + 1;
		blockPage = (currentPage / blockPageCount) + 1;
		lastPageNum = ((currentPage / blockPageCount) + 1) * blockPageCount;
		
		if (totalPage < lastPageNum) {
			lastPageNum = totalPage;
		}
	}
	
}
