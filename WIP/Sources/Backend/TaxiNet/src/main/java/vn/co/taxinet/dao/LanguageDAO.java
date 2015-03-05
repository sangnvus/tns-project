package vn.co.taxinet.dao;

import java.util.List;

import vn.co.taxinet.orm.Language;

public interface LanguageDAO extends BaseDAO {
	public Language findById(java.lang.String id);

	/**
	 * @category list all languages for dropdown list
	 * @author Ecchi
	 * @return
	 */
	public List<Language> listAllLanguage();
}
