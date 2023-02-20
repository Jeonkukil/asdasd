package shop.mtcoding.momo.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.momo.dto.board.BoardResp.BoardMainRespDto;

// F - DS - C - S - R - MYBATIS - DB 
@MybatisTest
public class LoveRepositoryTest {

    // @Autowired
    // private LoveRepository loveRepository;

    // @Test
    // public void findByBoardIdAndUserId_test() throws Exception {
    // // given //특정 레파지스토리를 테스트하겠다.
    // int boardId = 1;
    // int userId = 1;

    // // ObjectMapper 는 Timestamp 파싱을 못함!!
    // // ObjectMapper om = new ObjectMapper(); // Jackson

    // // when
    // Love lovePS = loveRepository.findByBoardIdAndUserId(boardId, userId);

    // // String responseBody = om.writeValueAsString(lovePS);
    // // System.out.println("테스트 : " + responseBody);
    // // then
    // assertThat(lovePS.getBoardId()).isEqualTo(1);
    // assertThat(lovePS.getUserId()).isEqualTo(1);
    // }
}
