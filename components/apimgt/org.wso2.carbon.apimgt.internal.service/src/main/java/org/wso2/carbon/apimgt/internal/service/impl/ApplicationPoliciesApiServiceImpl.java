/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.apimgt.internal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.derby.iapi.util.StringUtil;
import org.wso2.carbon.apimgt.api.model.subscription.ApplicationPolicy;
import org.wso2.carbon.apimgt.impl.dao.SubscriptionValidationDAO;
import org.wso2.carbon.apimgt.internal.service.ApplicationPoliciesApiService;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.wso2.carbon.apimgt.internal.service.utils.SubscriptionValidationDataUtil;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

public class ApplicationPoliciesApiServiceImpl implements ApplicationPoliciesApiService {

    @Override
    public Response applicationPoliciesGet(String xWSO2Tenant, String policyName, MessageContext messageContext) {

        SubscriptionValidationDAO subscriptionValidationDAO = new SubscriptionValidationDAO();

        if (StringUtils.isNotEmpty(xWSO2Tenant)) {
            if (StringUtils.isNotEmpty(policyName)) {
                List<ApplicationPolicy> model = new ArrayList<>();
                ApplicationPolicy applicationPolicy = subscriptionValidationDAO.
                        getApplicationPolicyByNameForTenant(policyName, xWSO2Tenant);
                if (applicationPolicy != null) {
                    model.add(applicationPolicy);
                }
                return Response.ok().entity(SubscriptionValidationDataUtil.
                        fromApplicationPolicyToApplicationPolicyListDTO(model)).build();

            } else {
                return Response.ok().entity(SubscriptionValidationDataUtil.
                        fromApplicationPolicyToApplicationPolicyListDTO(subscriptionValidationDAO.
                                getAllApplicationPolicies(xWSO2Tenant))).build();
            }
        } else {
            if (StringUtils.isNotEmpty(policyName)) {
                return Response.status(Response.Status.BAD_REQUEST.getStatusCode(),
                        "X-WSo2-Tenant header is missing.").build();
            }
        }
        return Response.ok().entity(SubscriptionValidationDataUtil.
                fromApplicationPolicyToApplicationPolicyListDTO(subscriptionValidationDAO.
                        getAllApplicationPolicies())).build();
    }
}