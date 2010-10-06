/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package com.devnexus.ting.core.service.exception;

/**
 * @author Gunnar Hillert
 * @version $Id:DuplicateUserException.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class DuplicateUserException extends Exception {


    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -5935393816289263497L;

    /**
     * Constructor.
     */
    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserException(Throwable cause) {
        super(cause);
    }
}
