
package vn.co.taxinet.dao;
import java.util.List;

import vn.co.taxinet.orm.ReferenceData;

public interface ReferenceDataDAO extends BaseDAO {
	List<ReferenceData> selectAll();
}

