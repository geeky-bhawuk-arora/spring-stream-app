import { Upload, Loader } from 'lucide-react';
import Message from './Message';
import ProgressBar from './ProgressBar';

export default function UploadSection({
  uploadData,
  setUploadData,
  uploading,
  uploadProgress,
  message,
  fileInputRef,
  onFileSelect,
  onUpload,
}) {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-lg font-semibold text-gray-900 mb-3">Upload Video</h2>
        <div className="bg-white rounded-lg border border-gray-200 p-6 space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Select Video File
            </label>
            <button
              onClick={() => fileInputRef.current?.click()}
              disabled={uploading}
              className="w-full px-4 py-3 border-2 border-dashed border-gray-300 rounded-lg hover:border-blue-400 hover:bg-blue-50 transition disabled:opacity-50"
            >
              <input
                ref={fileInputRef}
                type="file"
                onChange={onFileSelect}
                accept="video/*"
                className="hidden"
                disabled={uploading}
              />
              <div className="flex items-center justify-center space-x-2">
                <Upload className="w-4 h-4 text-gray-600" />
                <span className="text-sm text-gray-600">
                  {uploadData.file ? `✓ ${uploadData.file.name}` : 'Click to select video'}
                </span>
              </div>
            </button>
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">Video Title</label>
            <input
              type="text"
              value={uploadData.title}
              onChange={(e) => setUploadData({ ...uploadData, title: e.target.value })}
              placeholder="Enter video title..."
              disabled={uploading}
              className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-50"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">Description</label>
            <textarea
              value={uploadData.description}
              onChange={(e) => setUploadData({ ...uploadData, description: e.target.value })}
              placeholder="Enter video description..."
              rows="3"
              disabled={uploading}
              className="w-full px-3 py-2 border border-gray-300 rounded-md text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:bg-gray-50 resize-none"
            />
          </div>

          {uploading && <ProgressBar progress={uploadProgress} />}
          {message && <Message message={message} />}

          <button
            onClick={onUpload}
            disabled={uploading || !uploadData.file}
            className="w-full px-4 py-2 bg-blue-600 text-white rounded-md font-medium hover:bg-blue-700 transition disabled:bg-gray-400 flex items-center justify-center gap-2"
          >
            {uploading ? (
              <>
                <Loader className="w-4 h-4 animate-spin" />
                Uploading...
              </>
            ) : (
              <>
                <Upload className="w-4 h-4" />
                Upload Video
              </>
            )}
          </button>
        </div>
      </div>
    </div>
  );
}