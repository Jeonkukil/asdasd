package shop.mtcoding.momo.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoveRepository {

        public Love findByBoardIdAndUserId(@Param("boardId") int boardId, @Param("userId") int userId);

        public List<Love> findAll();

        public Love findById(int id);

        public int insert(Love Love);

        public int updateById(Love Love);

        public int deleteById(int id);
}