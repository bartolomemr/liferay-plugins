/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.bi.report.model.impl;

import com.liferay.bi.report.model.RequestedReport;
import com.liferay.documentlibrary.NoSuchDirectoryException;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.CompanyConstants;

/**
 * <a href="RequestedReportImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RequestedReportImpl extends RequestedReportModelImpl
	implements RequestedReport {
	public RequestedReportImpl() {
	}
	
	public String getAttachmentsDir() {

		if (_attachmentDirs == null) {
			_attachmentDirs = REPORTS_DIRS + getRequestId();
		}
		return _attachmentDirs;
	}

	public void setAttachmentsDir(String attachmentsDir) {

		_attachmentDirs = attachmentsDir;
	}

	public String[] getAttachmentsFiles()
		throws PortalException, SystemException {

		String[] fileNames = new String[0];

		try {
			fileNames =
				DLServiceUtil.getFileNames(
					getCompanyId(), CompanyConstants.SYSTEM,
					getAttachmentsDir());
		}
		catch (NoSuchDirectoryException nsde) {
		}

		return fileNames;
	}

	private String _attachmentDirs;
	private static String REPORTS_DIRS = "reports_dir/";
}