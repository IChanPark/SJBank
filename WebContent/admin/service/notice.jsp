<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style media="screen">
.ltt {
    width: 100%;
    text-align: center;
  }
.ull{
	
    display: none;
}
.lii {
	
  	display: block;
}
.d1 {padding-top: 10px;  height: 30px; margin: auto; }
.m {background-color: #3C0; float: left; height: 30px;  width: 100%;
display: grid;justify-content: center;align-items: center;}

</style>

            <div>
                <h1>공지사항 관리</h1>
            </div>
            <div class="row">
                  <div>
                      <button type="button" class="btn btn-list">
                       									   공지사항 작성
                      </button>
                  </div>
              </div>
            <div>
                <div>공지사항 </div>
                <div>
                    <table class="table table-hover" border="1">
                        <thead>
                            <tr>
                                <th>No.1</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
                       
                        <c:forEach items="${data }" var="notice">
                            <tr>
                                <td>${notice.seq}</td>
                                <td>${notice.title}</td>
                                <td>${notice.id}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${notice.register_date}"/></td>
                                <td>${notice.content}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
    
