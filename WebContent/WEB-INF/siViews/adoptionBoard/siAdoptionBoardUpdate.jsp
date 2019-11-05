<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Header --%>
<jsp:include page="/WEB-INF/common/header.jsp" />

	<script>
		$(document).ready(function(){
			$("#delFileBtn").click(function(){
				if(confirm("첨부파일을 삭제하시겠습니까?")){
					$(".delFile").hide();
					$(".adoptionBoardFilename").show();
					$("#status").val("delete");
				}
			});
		});
	</script>

	<section id="content-wrapper">
		<div class="area">
			<h2 class="comm-content-tit">입양후기 글 수정</h2>
			<div class="common-tbl-box">
				<form action="/siAdoptionBoardUpdate?adoptionBoardNo=${adoptionBoard.adoptionBoardNo }" method="post" enctype="multipart/form-data">
					<table class="comm-tbl">
						<colgroup>
							<col width="28%">
							<col width="/">
						</colgroup>
						<tr>
							<th style="text-align:center;">제목</th>
							<td><input type="text" name="adoptionBoardTitle" placeholder="글 제목" value="${adoptionBoard.adoptionBoardTitle }"></td>
						</tr>
						<tr>
							<th style="text-align:center;">작성자</th>
							<td>${sessionScope.member.name }</td>
						</tr>
						<tr>
							<th style="text-align:center;">파일첨부</th>
							<td>
								<input type="hidden" name="status" id="status" value="stay">
								<!-- 삭제 유무 판단용 input태그 -->
								<c:choose>
									<c:when test="${not empty adoptionBoard.adoptionBoardFilepath }">
										<div class="filebox">
											<img class="delFile" src="/siUpload/adoptionBoard/${adoptionBoard.adoptionBoardFilename }" width="16px">
											<input type="text" id="fileName" class="adoptionBoardFilename" style="width:72.5%;display:none;" />
											<label for="adoptionBoardname" class="adoptionBoardFilename" style="display:none;">파일 업로드</label>
											<input type="file" id="adoptionBoardname" class="adoptionBoardFilename" name="adoptionBoardFilename" style="display:none;"
												onchange="javascript: document.getElementById('fileName').value = this.value.replace(/c:\\fakepath\\/i, '')" required/>
											<span class="delFile">${adoptionBoard.adoptionBoardFilename }</span>
											<button type="button" id="delFileBtn" class="delFile">삭제하기</button>
											<input type="hidden" name="adoptionBoardOldFilename" value=${adoptionBoard.adoptionBoardFilename }>
											<input type="hidden" name="adoptionBoardOldFilepath" value=${adoptionBoard.adoptionBoardFilepath }>
											<br/>
										</div>
									</c:when>
									<c:otherwise>
										<div class="filebox">
											<input type="text" id="fileName" class="adoptionBoardFilename" style="width:72.5%;" />
											<label for="adoptionBoardname" class="adoptionBoardFilename">파일 업로드</label>
											<input type="file" id="adoptionBoardname" class="adoptionBoardFilename" name="adoptionBoardFilename"
												onchange="javascript: document.getElementById('fileName').value = this.value.replace(/c:\\fakepath\\/i, '')" />
											<br/>
										</div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th style="text-align:center;">내용</th>
							<td><textarea name="adoptionBoardContent" rows="30" style="resize:none;" placeholder="글 내용" maxlength="2048">${adoptionBoard.adoptionBoardContent }</textarea></td>
						</tr>
					</table>
					<div class="common-tbl-btn-group">
						<button type="submit" class="btn-style1" style="border:0;">수정하기</button>
						<button type="reset" class="btn-style2" onclick="location.href='siAdoptionBoardView?adoptionBoardNo=${adoptionBoard.adoptionBoardNo }'">취소</button>
					</div>
				</form>
			</div>
		</div>
	</section>
		<style>
		.filebox label,button {
			  display: inline-block;
			  padding: .5em .75em;
			  color: #fff;
			  font-size: inherit;
			  line-height: normal;
			  vertical-align: middle;
			  background-color: #444;
			  cursor: pointer;
			  border: 1px solid #444;
			  border-radius: .25em;
			  -webkit-transition: background-color 0.2s;
			  transition: background-color 0.2s;
		}
		.filebox label,button:hover {
		  background-color: #444;
		}
		
		.filebox label,button:active {
		  background-color: #444;
		}
		
		.filebox input[type="file"] {
		  position: absolute;
		  width: 1px;
		  height: 1px;
		  padding: 0;
		  margin: -1px;
		  overflow: hidden;
		  clip: rect(0, 0, 0, 0);
		  border: 0;
		}
	</style>
<%-- Footer --%>
<jsp:include page="/WEB-INF/common/footer.jsp" />